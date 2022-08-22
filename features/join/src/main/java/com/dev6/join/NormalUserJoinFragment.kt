package com.dev6.join

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.data.model.JoinEntitiy
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.join.databinding.FragmentNormalUserJoinBinding
import com.dev6.join.viewmodel.JoinViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.IOException


class NormalUserJoinFragment :
    BindingFragment<FragmentNormalUserJoinBinding>(R.layout.fragment_normal_user_join) {

    private val PICK_IMAGE_REQUEST = 1
    private val joinViewModel: JoinViewModel by activityViewModels()
    lateinit var joinDto: JoinEntitiy
    lateinit var userType: String
    private lateinit var imageUri: Uri


    override fun initView() {
        super.initView()

        userType = arguments?.get("userType").toString()

    }

    override fun initViewModel() {
        super.initViewModel()
        joinViewModel.initJoinImage()
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
                        imageUri = uriList[0]

                        Glide.with(binding.root)
                            .load(imageUri)
                            .circleCrop()
                            .into(binding.profileImageButton)
                    }

            }

            noramlJoinBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun makeJoinDto(): JoinEntitiyRepo {
        return JoinEntitiyRepo(
            joinDto.loginMethod, joinDto.uid,
            "", "",
            "", binding.nickNameInputText.text.toString(), "", "",
            "",
            "", "", "" ,userType,""
        )
    }

}