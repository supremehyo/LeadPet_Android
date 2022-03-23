package com.dev6.login


import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.login.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {





    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var activityLauncher: ActivityResultLauncher<Intent>



    override fun initListener() {
        super.initListener()



        //todo 데이터 바인딩을 통해 viewmodel로 옮기게될 로직들
        binding.imageButton.setOnClickListener {
            kakaoLogin()
        }

        //todo navigation 적용을 통해 사라질 로직
        binding.someId.setOnClickListener {
        findNavController().navigate(R.id.action_loginFragment_to_emailLoginFragment)
        }

        binding.googleButton.setOnClickListener {
            signIn()
        }

    }

    private fun kakaoLogin() {

//        UserApiClient.instance.me { user, error ->
//            if (error != null) {
//                Log.e(TAG, "사용자 정보 요청 실패", error)
//            }
//            else if (user != null) {
//                //todo 회원 번호 id 는 uuid?
//                user.id
//                Log.i(TAG, "사용자 정보 요청 성공" +
//                        "\n회원번호: ${user.id}" +
//                        "\n이메일: ${user.kakaoAccount?.email}" +
//                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
//                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
//            }
//        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Timber.e("카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Timber.i("카카오계정으로 로그인 성공 ${token.accessToken}")

            }
        }

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) {
                    Timber.e("카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(
                        requireContext(),
                        callback = callback
                    )
                } else if (token != null) {
                    Timber.i("카카오톡으로 로그인 성공 ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())

    }

    override fun afterViewCreated() {
        super.afterViewCreated()



        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        auth = Firebase.auth
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

                try {
                    val account = task.getResult(ApiException::class.java)!!


                    Timber.d("GoogleLogin", "firebaseAuthWithGoogle: " + account.id)
                } catch (e: ApiException) {
                    Timber.d("GoogleLogin", "Google sign in failed: " + e.message)
                }
            }
        }

    }

    private fun signIn() = activityLauncher.launch(googleSignInClient!!.signInIntent)

}
