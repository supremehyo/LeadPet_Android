package com.dev6.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.dev6.core.R
import com.dev6.core.databinding.CustomViewCameraButtonBinding

class CustomButtonCamera @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: CustomViewCameraButtonBinding

    init {
        binding = CustomViewCameraButtonBinding.inflate(LayoutInflater.from(context), this, true)

        attrs?.run {
//            val typedArr = context.obtainStyledAttributes(attrs, R.styleable.SpinnerButton)
//            setText(typedArr.getString(R.styleable.SpinnerButton_text) ?: "값없음")
        }
    }


//    fun setText(text_string: String) {
//        binding.text.text = text_string
//    }

}