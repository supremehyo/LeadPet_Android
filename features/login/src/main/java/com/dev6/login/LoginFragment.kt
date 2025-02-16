package com.dev6.login

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.LoginType
import com.dev6.domain.model.Login
import com.dev6.login.databinding.FragmentLoginBinding
import com.dev6.login.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var activityLauncher: ActivityResultLauncher<Intent>

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun initListener() {
        super.initListener()
        with(binding) {
            ibKakao.setOnClickListener {
                kakaoLogin()
            }

            someId.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_emailLoginFragment)
            }
            googleButton.setOnClickListener {
                signIn()
            }
        }
    }

    // activity에서 제외시킬수가 없다(특정 acitivity context를 콕찝어서 사용해야함)

    private fun kakaoLogin() {
        CoroutineScope(Dispatchers.Main.immediate).launch {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                runCatching {
                    suspendCancellableCoroutine<Result<String>> { cancellableContinuation ->
                        UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                            cancellableContinuation.resume(
                                when {
                                    error != null -> Result.failure(error)
                                    token != null -> Result.success(token.accessToken)
                                    else -> Result.failure(Exception("Kakao API response is nothing."))
                                }
                            )
                        }
                    }.getOrThrow()
                }.onSuccess { token ->
                    loginViewModel.setLogin(Login(LoginType.KAKAO, token, null, null))
                    loginViewModel.getlogin()
                }.onFailure {
                    Toast.makeText(
                        this@LoginFragment.requireContext().applicationContext,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        GoogleSignIn.getLastSignedInAccount(requireContext())?.let {
            loginViewModel.setLogin(Login(LoginType.GOOGLE, it.id, null, null))
            loginViewModel.getlogin()
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        auth = Firebase.auth
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        activityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        val account = task.getResult(ApiException::class.java)!!

                        loginViewModel.setLogin(
                            Login(
                                LoginType.GOOGLE,
                                account.id,
                                null,
                                null
                            )
                        )
                        loginViewModel.getlogin()
                        Timber.d("GoogleLogin ${account.id}")
                    } catch (e: ApiException) {
                        Timber.d("GoogleLogin  ${e.message}")
                    }
                }
            }
    }

    private fun signIn() = activityLauncher.launch(googleSignInClient!!.signInIntent)
}
