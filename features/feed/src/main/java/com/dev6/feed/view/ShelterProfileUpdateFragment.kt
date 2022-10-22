package com.dev6.feed.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.FeedViewType
import com.dev6.core.util.ImageUpload
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.NormalUserUpdateRepo
import com.dev6.domain.model.ProfileUserUpdateRepo
import com.dev6.domain.model.ShelterResopnseEntitiyRepo
import com.dev6.domain.model.User
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentShelterProfileUpdateBinding
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.ProfileViewModel
import gun0912.tedimagepicker.builder.TedImagePicker

class ShelterProfileUpdateFragment :
    BindingFragment<FragmentShelterProfileUpdateBinding>(R.layout.fragment_shelter_profile_update) {
    private val profileViewModel : ProfileViewModel by activityViewModels()
    private val feedViewModel : FeedViewModel by activityViewModels()
    private var profileImage : String = ""
    private lateinit var    imageUpload : ImageUpload
    override fun initView() {
        super.initView()
        feedViewModel.setCurrentView(FeedViewType.PROFILEUPDATE)

        binding.IntroUpdateInputText.setText(UserData.shelterIntro)
        binding.AccountUpdateInputText.setText(UserData.shelterAccount)
        binding.HomepageUpdateInputText.setText(UserData.shelterHomepage)
        binding.PhoneUpdateInputText.setText(UserData.shelterPhoneNumber)
        binding.citySelectBt.setText(UserData.userCity)



    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
        binding.shelterProfileUpdateTv.setOnClickListener {

            var shelterResopnseEntitiyRepo = ProfileUserUpdateRepo(
                "",
                UserData.shelterAccount?: "",
                UserData.shelterAddress?: "",
                UserData.shelterHomepage?: "",
                UserData.shelterIntro?: "",
                UserData.shelterManager?: "",
                UserData.shelterName?: "",
                UserData.shelterPhoneNumber?: "",
                byteArrayOf()
            //profileImage 추가 예정
            )
            profileViewModel.updateShelterProfileData(shelterResopnseEntitiyRepo, UserData.userId)


        }
        binding.shelterProfileImageIv.setOnClickListener {
            TedImagePicker.with(requireContext())
                .max(1, "")
                .startMultiImage { uriList ->
                    val imageByte: ByteArray =
                    ImageUpload.compressBitmap(ImageUpload.convertUrlToBitmap(uriList[0], this.requireContext()))
                    profileViewModel.setPostImage(imageByte)

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
                    UserData.profileImage = urlList[0].toString()
                    Glide.with(this@ShelterProfileUpdateFragment).load(urlList[0])
                        .into(binding.shelterProfileImageIv)
                }
            }
        }
    }

}