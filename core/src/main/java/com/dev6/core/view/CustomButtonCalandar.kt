package com.dev6.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.dev6.core.R
import com.dev6.core.databinding.CustomViewCaladerBinding
import com.dev6.core.databinding.CustomViewSpinnerButtonBinding


class CustomButtonCalandar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    
    private var binding: CustomViewCaladerBinding
    
    init {
        binding = CustomViewCaladerBinding.inflate(LayoutInflater.from(context), this, true)
        
        binding.textInputLayout.isEnabled = false
        binding.root.setOnClickListener {
            Toast.makeText(context,"호호" , Toast.LENGTH_SHORT).show()
        }
        attrs?.run {
            
            val typedArr = context.obtainStyledAttributes(attrs, R.styleable.Button)
            setText(typedArr.getString(R.styleable.Button_text) ?: "")
            if (!isInEditMode) {
                typedArr.recycle()
            }
        }
    }
    
    
    fun setText(text_string: String) {
        binding.textInputLayout.editText?.setText(text_string)
    }
    
}