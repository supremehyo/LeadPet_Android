package com.dev6.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.dev6.core.databinding.CustomViewCameraButtonBinding

class CustomButtonCamera @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: CustomViewCameraButtonBinding

    init {
        binding = CustomViewCameraButtonBinding.inflate(LayoutInflater.from(context), this)
    }

    fun getImage(): ImageView {
        with(binding) {
            ivCamera.visibility = View.INVISIBLE
            binding.tvCamera.visibility = View.INVISIBLE
        }
        return binding.vCamera
    }
}
