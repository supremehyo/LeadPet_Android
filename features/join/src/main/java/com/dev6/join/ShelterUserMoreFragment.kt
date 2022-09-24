package com.dev6.join

import android.content.Intent
import android.net.Uri
import android.service.autofill.UserData
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.LoginType
import com.dev6.core.util.ImageUpload
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.Join
import com.dev6.feed.view.FeedActivity
import com.dev6.join.databinding.FragmentShelterUserMoreBinding
import com.dev6.join.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShelterUserMoreFragment :
    BindingFragment<FragmentShelterUserMoreBinding>(R.layout.fragment_shelter_user_more) {

    private val joinViewModel: JoinViewModel by activityViewModels()
    lateinit var joinDto: Join
    private lateinit var imageUri: String
    lateinit var imageUpload: ImageUpload

    private lateinit var userType: String
    private lateinit var shelterName: String
    private lateinit var shelterPhone: String
    private lateinit var shelterAccount: String
    private lateinit var shelterAddress: String
    private lateinit var shelterHomePage: String
    private lateinit var shelterIntro: String
    private lateinit var joinImages: List<Uri>
    lateinit var loginType: String

    override fun initView() {
        super.initView()
        userType = arguments?.get("userType").toString()
        shelterName = arguments?.get("shelterName").toString()
        shelterPhone = arguments?.get("shelterPhone").toString()
        shelterAccount = arguments?.get("shelterAccount").toString()
        shelterAddress = arguments?.get("shelterAddress").toString()
        shelterHomePage = arguments?.get("shelterHomePage").toString() ?: ""
        shelterIntro = ""

    }

    override fun initListener() {
        super.initListener()
        binding.apply {
            additionalProfileButton.setOnClickListener {
                if (shelterDescriptionTv.text.toString().isNotEmpty() && joinImages.isNotEmpty()) {

                    imageUpload.uploadPhoto(shelterName, joinImages[0], requireContext()) {
                        imageUri = it
                        joinViewModel.signUp(
                            makeJoinDto(
                                shelterName,
                                shelterPhone,
                                shelterAccount,
                                shelterAddress,
                                shelterHomePage,
                                userType,
                                shelterDescriptionTv.text.toString()
                            )
                        )
                    }
                } else {
                    Toast.makeText(context, "소개글이 비어있습니다.", Toast.LENGTH_SHORT).show()
                }
            }
/*
            additionalProfileButtonNext.setOnClickListener {
                joinViewModel.signUp(
                    makeJoinDto(
                        shelterName,
                        shelterPhone,
                        shelterAccount,
                        shelterAddress,
                        shelterHomePage,
                        userType,
                        shelterIntro
                    )
                )
            }

 */

            shelterMoreleftArrow.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            joinViewModel.joinImageFlow.collect {
                joinImages = it
            }
        }

        repeatOnStarted {
            joinViewModel.joinEvnetFlow.collect { event ->
                when (event) {
                    is JoinViewModel.Event.UiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                val feedIntent = Intent(context, FeedActivity::class.java)
                                feedIntent.putExtra("userType", userType)
                                startActivity(feedIntent)
                            }
                            is UiState.Error -> {
                                Toast.makeText(context, "실패 했어여", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(context, "로딩 했어여", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

        }
    }

    private fun makeLoginType(loginTypeEnum: LoginType): String {
        loginType = when (loginTypeEnum) {
            LoginType.EMAIL -> {
                return "EMAIL"
            }
            LoginType.KAKAO -> {
                return "KAKAO"
            }
            LoginType.GOOGLE -> {
                return "GOOGLE"
            }
        }


    }

    fun makeJoinDto(
        shelterName: String,
        shelterPhone: String,
        shelterAccount: String,
        shelterAddress: String,
        shelterHomePage: String,
        userType: String,
        shelterIntro: String
    ): Join {
        return Join(
            makeLoginType(com.dev6.core.UserData.loginMethod), com.dev6.core.UserData.uid,
            "", "",
            imageUri, shelterName, shelterName, shelterAddress, shelterAccount,
            shelterPhone,
            "", shelterHomePage, userType, shelterIntro
        )
    }
}