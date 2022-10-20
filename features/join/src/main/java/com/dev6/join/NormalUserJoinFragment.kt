package com.dev6.join


import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.LoginType
import com.dev6.core.util.ImageUpload
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.data.model.JoinResponse
import com.dev6.domain.model.Join
import com.dev6.feed.view.FeedActivity
import com.dev6.join.databinding.FragmentNormalUserJoinBinding
import com.dev6.join.viewmodel.JoinViewModel
import gun0912.tedimagepicker.builder.TedImagePicker

class NormalUserJoinFragment :
    BindingFragment<FragmentNormalUserJoinBinding>(R.layout.fragment_normal_user_join) {

    private val joinViewModel: JoinViewModel by activityViewModels()
    lateinit var userType: String
    lateinit var uid : String
    lateinit var loginType: String


    override fun initView() {
        super.initView()
        userType = arguments?.get("userType").toString()
        uid = arguments?.get("uid").toString()


    }

    override fun initViewModel() {
        super.initViewModel()
        joinViewModel.initJoinImage()


        repeatOnStarted {
            joinViewModel.joinEvnetFlow.collect { event ->
                when (event) {
                    is JoinViewModel.Event.UiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                event.uiState.data
                                Toast.makeText(context, "성공 했어여", Toast.LENGTH_SHORT).show()
                                requireActivity().finish()
                                /*
                                val feedIntent = Intent(context, FeedActivity::class.java)
                                feedIntent.putExtra("userType", userType)
                                startActivity(feedIntent)

                                 */
                            }
                            is UiState.Error -> {
                                Toast.makeText(context, "실패 했어여", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(context, "로딩 했어여", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else->{

                }
                }
            }
        }

    }

    override fun initListener() {
        super.initListener()

        binding.apply {
            profileButton.setOnClickListener {
                if (nickNameInputText.text.toString().isNotEmpty()) {
                    joinViewModel.signUp(makeJoinDto())
                } else if (nickNameInputText.text.toString().isEmpty()) {
                    Toast.makeText(context, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else if (nickNameInputText.text.toString().length < 2 || nickNameInputText.text.toString().length > 16) {
                    Toast.makeText(context, "닉네임의 길이가 적합하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            profileImageButton.setOnClickListener {
                TedImagePicker.with(requireContext())
                    .max(1, "")
                    .startMultiImage { uriList ->
                        val imageList: List<ByteArray> =
                            uriList.map { ImageUpload.convertUrlToBitmap(it, requireContext()) }
                                .map { ImageUpload.compressBitmap(it) }

                        joinViewModel.setProfileImage(imageList)
                    }
            }

            noramlJoinBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            joinViewModel.userUpdateImageFlow.collect { urlList ->
                if (urlList.isNotEmpty()) {
                    UserData.profileImage = urlList[0].toString()
                    Glide.with(requireContext()).load(urlList[0]).circleCrop()
                        .into(binding.profileImageButton)
                }
            }
        }

    }

    private fun makeLoginType(loginTypeEnum: LoginType) : String{
        loginType = when(loginTypeEnum){
            LoginType.EMAIL ->{
                return "EMAIL"
            }
            LoginType.KAKAO ->{
                return "KAKAO"
            }
            LoginType.GOOGLE ->{
                return "GOOGLE"
            }
        }
    }


    private fun makeJoinDto(): Join {
        return Join(
            makeLoginType(UserData.loginMethod), UserData.uid,
            "", "",
            "", binding.nickNameInputText.text.toString(), "", "",
            "",
            "", "", "" ,userType,"", emptyList()
        )
    }

}