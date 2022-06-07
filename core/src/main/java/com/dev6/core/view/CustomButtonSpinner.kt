package com.dev6.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.dev6.core.R
import com.dev6.core.databinding.CustomViewSpinnerButtonBinding


class CustomButtonSpinner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: CustomViewSpinnerButtonBinding

    init {
        binding = CustomViewSpinnerButtonBinding.inflate(LayoutInflater.from(context), this)

        attrs?.run {
            val typedArr = context.obtainStyledAttributes(attrs, R.styleable.Button)
            setText(typedArr.getString(R.styleable.Button_text) ?: "값없음")
            if (!isInEditMode) {
                typedArr.recycle()
            }
        }
    }


    fun setText(text_string: String) {
        binding.text.text = text_string
    }

}