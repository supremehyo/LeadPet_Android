package com.dev6.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnAttach
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.dev6.core.R
import com.dev6.core.databinding.CustomViewCaladerBinding
import com.dev6.core.util.extension.setClickEvent
import java.util.function.Consumer

class CustomButtonCalandar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var okClickListener: Consumer<String>
    private var binding: CustomViewCaladerBinding

    init {
        binding = CustomViewCaladerBinding.inflate(LayoutInflater.from(context), this)

        doOnAttach {
            findViewTreeLifecycleOwner()?.lifecycleScope?.let { lifeCycleScope ->
                binding.tietDate.setClickEvent(lifeCycleScope) {
                    okClickListener.accept("하하")
                }
            }
        }

        binding.tietDate.doOnTextChanged { text, _, _, _ ->
            if(text.isNullOrEmpty()) return@doOnTextChanged
            binding.textInputLayout.setBoxStrokeColorStateList(
                resources.getColorStateList(
                    R.color.text_input_layout_stroke_pressed,
                    null
                )
            )
        }
    }

    fun setText(text_string: String) {
        binding.textInputLayout.editText?.setText(text_string)
    }

    fun setHint(text_string: String) {
        binding.textInputLayout.hint = text_string
    }

    fun setClick(consumer: Consumer<String>) {
        this.okClickListener = consumer
    }
}
