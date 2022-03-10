package com.dev6.join

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.join.databinding.FragmentShelterUserMoreBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class ShelterUserMoreFragment : BindingFragment<FragmentShelterUserMoreBinding>(R.layout.fragment_shelter_user_more) {

    private val PICK_IMAGE_REQUEST = 1

    override fun initView() {
        super.initView()
        binding.apply {
            additionalProfileImageButton.setOnClickListener {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"),
                    PICK_IMAGE_REQUEST
                )
            }
            additionalProfileButton.setOnClickListener {
                findNavController().navigate(R.id.action_shelterUserMoreFragment_to_shelterDonateFragment)
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
                val imageView: ImageView = requireActivity()!!.findViewById<View>(R.id.additionalProfileImageButton) as ImageView
                Glide.with(this).load(bitmap).circleCrop().into(imageView)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}