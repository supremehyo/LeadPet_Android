package com.dev6.join

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.core.util.ImageUpload
import com.dev6.join.databinding.FragmentShelterUserJoinBinding
import com.dev6.join.viewmodel.JoinViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import java.io.IOException


class ShelterUserJoinFragment :
    BindingFragment<FragmentShelterUserJoinBinding>(R.layout.fragment_shelter_user_join) {

    private val PICK_IMAGE_REQUEST = 1
    private val joinViewModel: JoinViewModel by activityViewModels()
    private lateinit var imageUri: String
    lateinit var imageUpload: ImageUpload

    private lateinit var userType: String
    override fun initView() {
        super.initView()

    }

    override fun initListener() {
        super.initListener()

        userType = arguments?.get("userType").toString()

        binding.apply {
            shelterProfileImageButton.setOnClickListener {
                TedImagePicker.with(requireContext())
                    .max(1, "")
                    .startMultiImage { uriList ->
                        val imageList: List<ByteArray> =
                            uriList.map { ImageUpload.convertUrlToBitmap(it, requireContext()) }
                                .map { ImageUpload.compressBitmap(it) }

                        joinViewModel.setProfileImage(imageList)
                        Glide.with(binding.root)
                            .load(uriList[0])
                            .circleCrop()
                            .into(binding.shelterProfileImageButton)
                    }
            }

            joinTypeBackButton.setOnClickListener {
                findNavController().popBackStack()
            }

            shelterProfileButton.setOnClickListener {
                var shelterName = shelterNameTv.text.toString()
                var shelterPhone = shelterPhoneTv.text.toString()
                var shelterAccount = shelterAccountTv.text.toString()
                var shelterAddress = shelterAddressTv.text.toString()
                var shelterHomePage = shelterHomePageTv.text.toString()

                if (shelterName.isNotEmpty() && shelterPhone.isNotEmpty()
                    && shelterAccount.isNotEmpty() && shelterAddress.isNotEmpty()
                ) {
                    findNavController().navigate(R.id.action_shelterUserJoinFragment_to_shelterUserMoreFragment,
                        Bundle().apply {
                            putString("shelterName", shelterName)
                            putString("shelterPhone", shelterPhone)
                            putString("shelterAccount", shelterAccount)
                            putString("shelterAddress", shelterAddress)
                            putString("shelterHomePage", shelterHomePage)
                            putString("userType", userType)
                        }
                    )
                } else {
                    Toast.makeText(context, "입력되지 않은 값이 있습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun initViewModel() {
        super.initViewModel()
        joinViewModel.initJoinImage()
    }
}