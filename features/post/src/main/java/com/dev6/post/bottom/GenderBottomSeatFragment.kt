package com.dev6.post.bottom

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev6.core.base.BaseBottomSheetDialogFragment
import com.dev6.post.R
import com.dev6.post.databinding.FragmentBottomSeatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenderBottomSeatFragment :
    BaseBottomSheetDialogFragment<FragmentBottomSeatBinding>(R.layout.fragment_bottom_seat) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initListener() {
        binding.include.tvLeft.background =
            resources.getDrawable(
                R.drawable.ic_close_thin_black,
                null
            )

        binding.include.tvRight.run {
            setTextColor(resources.getColor(R.color.Main, null))
            setText(R.string.text_complete)
            setOnClickListener {

                this@GenderBottomSeatFragment.dialog?.dismiss()
            }
        }
        binding.include.tvLeft.setOnClickListener {
            this@GenderBottomSeatFragment.dialog?.dismiss()
        }
    }

    override fun setCancel(setting: Boolean) {
        super.setCancel(setting)
    }

    override fun setWindowFeature(feature: Int) {
        super.setWindowFeature(feature)
    }

    override fun setBackground(drawable: ColorDrawable) {
        super.setBackground(drawable)
    }
}