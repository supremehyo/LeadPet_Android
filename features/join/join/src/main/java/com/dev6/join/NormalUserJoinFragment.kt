package com.dev6.join

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.data.entity.JoinEntitiy
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.join.databinding.FragmentNormalUserJoinBinding
import com.dev6.join.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException


class NormalUserJoinFragment : BindingFragment<FragmentNormalUserJoinBinding>(R.layout.fragment_normal_user_join) {

    private val PICK_IMAGE_REQUEST = 1
    private  val joinViewModel : JoinViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        binding.apply {
            profileImageButton.setOnClickListener {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"),
                    PICK_IMAGE_REQUEST
                )
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.profileButton.setOnClickListener {
            val joindto =JoinEntitiy(
                "email" , "1111" ,
                "dev6@gmail.com","1234",
                "",binding.normalUserNicknameEt.text.toString()
                ,"","",
                "",
                "","")
            joinViewModel.signUp(joinEntitiyRepo = joindto)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === PICK_IMAGE_REQUEST && resultCode === Activity.RESULT_OK && data != null && data.data != null) {
            val uri: Uri? = data.data
            try {
                val bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity()!!.contentResolver, uri)
                val imageView: ImageView = requireActivity()!!.findViewById<View>(R.id.profileImageButton) as ImageView
                Glide.with(this).load(bitmap).circleCrop().into(imageView)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}