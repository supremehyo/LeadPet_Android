package com.dev6.join

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.join.databinding.FragmentShelterUserJoinBinding
import java.io.IOException


class ShelterUserJoinFragment : BindingFragment<FragmentShelterUserJoinBinding>(R.layout.fragment_shelter_user_join) {

    private val PICK_IMAGE_REQUEST = 1

    override fun initView() {
        super.initView()

    }

    override fun initListener() {
        super.initListener()

        binding.apply {
            shelterProfileImageButton.setOnClickListener {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"),
                    PICK_IMAGE_REQUEST
                )
            }

            profileButton.setOnClickListener {
                if (shelterNickname.text.toString().isNotEmpty()){
                    findNavController().navigate(R.id.action_shelterUserJoinFragment_to_shelterUserMoreFragment)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === PICK_IMAGE_REQUEST && resultCode === Activity.RESULT_OK && data != null && data.data != null) {
            val uri: Uri? = data.data
            try {
                val bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity()!!.contentResolver, uri)
                val imageView: ImageView = requireActivity()!!.findViewById<View>(R.id.shelter_profileImageButton) as ImageView
                Glide.with(this).load(bitmap).circleCrop().into(imageView)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}