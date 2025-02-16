package com.dev6.feed.view

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.core.util.ImageUpload
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.NormalUserUpdateRepo
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentUserProfileUpdateBinding
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.ProfileViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.coroutines.flow.collectLatest


class UserProfileUpdateFragment : BindingFragment<FragmentUserProfileUpdateBinding>(R.layout.fragment_user_profile_update) {
    private val profileViewModel : ProfileViewModel by activityViewModels()
    private val feedViewModel : FeedViewModel by activityViewModels()
    private var cityName = ""
    private var profileImage = ""
    private lateinit var    imageUpload : ImageUpload
    override fun initView() {
        super.initView()
        feedViewModel.setCurrentView(FeedViewType.PROFILEUPDATE)
//        imageUpload = ImageUpload()
        //profileImage = UserData.profileImage.toString()

        Glide.with(binding.root)
            .load(Uri.parse(UserData.profileImage))
            .error(R.drawable.dailay_image1)
            .circleCrop()
            .into(  binding.shelterProfileImageIv)

        binding.nickNameUpdateInputText.setText(UserData.userName)
        binding.IntroUpdateInputText.setText(UserData.intro)
        binding.citySelectBt.setText(UserData.userCity)
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            profileViewModel.cityChoiceStateFlow.collectLatest {
                cityName = it
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.citySelectBt.setOnClickListener {
            val bottomSheet = CityBottomSeatFragment()
            bottomSheet.show(parentFragmentManager,bottomSheet.tag)
        }

        binding.userProfileUpdateCompleteTv.setOnClickListener {

            var normalUserUpdateRepo = NormalUserUpdateRepo(
                cityName,
                binding.IntroUpdateInputText.text.toString(),
                binding.nickNameUpdateInputText.text.toString(),
                (it ?: UserData.profileImage).toString(),
                byteArrayOf()
            )
            profileViewModel.updateNormalUserProfileData(normalUserUpdateRepo,UserData.userId)

            /*
            if(profileImage == ""){
                var normalUserUpdateRepo = NormalUserUpdateRepo(
                    cityName,
                    binding.IntroUpdateInputText.text.toString(),
                    binding.nickNameUpdateInputText.text.toString(),
                    (it ?: UserData.profileImage).toString()
                )
                profileViewModel.updateNormalUserProfileData(normalUserUpdateRepo,UserData.userId)
            }else{
                imageUpload.uploadPhoto(UserData.uid,profileImage.toUri(),requireContext()){
                    var normalUserUpdateRepo = NormalUserUpdateRepo(
                        cityName,
                        binding.IntroUpdateInputText.text.toString(),
                        binding.nickNameUpdateInputText.text.toString(),
                        (it ?: UserData.profileImage).toString()
                    )
                    UserData.profileImage = it
                    profileViewModel.updateNormalUserProfileData(normalUserUpdateRepo,UserData.userId)
                }
            }

             */
        }

        binding.shelterProfileImageIv.setOnClickListener {
            TedImagePicker.with(requireContext())
                .max(1, "")
                .startMultiImage { uriList ->

                    val imageByte: ByteArray =
                        ImageUpload.compressBitmap(ImageUpload.convertUrlToBitmap(uriList[0], this.requireContext()))
                    profileViewModel.setPostImage(imageByte)

                    profileImage = uriList[0].toString()
                    Glide.with(binding.root)
                        .load(uriList[0])
                        .circleCrop()
                        .into(binding.shelterProfileImageIv)

                }
        }


    }

    override fun afterViewCreated() {
        super.afterViewCreated()

        repeatOnStarted {
            profileViewModel.postImageFlow.collect { urlList ->
                if (urlList.isNotEmpty()) {
                    //UserData.profileImage = urlList[0].toString()
                }
            }
        }
        repeatOnStartedFragment {
            profileViewModel.cityChoiceStateFlow.collect{
                binding.citySelectBt.text = it
            }
        }

        repeatOnStartedFragment {
            profileViewModel.eventNormalUserProfileUpdateDetail.collectLatest { event ->
                when (event) {
                    is ProfileViewModel.Event.NormalUserProfileUpdateUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                UserData.profileImage = profileImage
                                findNavController().popBackStack()
                            }
                            is UiState.Error -> {
                                Toast.makeText(
                                    activity,
                                    "실패 했어여",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(
                                    activity,
                                    "로딩 했어여",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    else -> {
                    }
                }
            }
        }



    }
}