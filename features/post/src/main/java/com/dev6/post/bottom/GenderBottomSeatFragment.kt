package com.dev6.post.bottom

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BaseBottomSheetDialogFragment
import com.dev6.core.enum.Gender
import com.dev6.post.R
import com.dev6.post.databinding.FragmentBottomSeatBinding
import com.dev6.post.viewmodel.AdoptPostViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GenderBottomSeatFragment :
    BaseBottomSheetDialogFragment<FragmentBottomSeatBinding>(R.layout.fragment_bottom_seat) {

    private val adoptPostViewModel: AdoptPostViewModel by activityViewModels()

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

    override fun initView() {
        binding.include.tvLeft.background =
            resources.getDrawable(
                R.drawable.ic_close_thin_black,
                null
            )
        binding.include.tvTop.text = "암수구분"
        binding.include.tvRight.run {
            setTextColor(resources.getColor(R.color.Main, null))
            setText(R.string.text_complete)
        }
    }

    override fun initListener() {
        binding.include.tvRight.setOnClickListener {
            val radioButtonText = when (binding.rgGender.checkedRadioButtonId) {
                R.id.mrb_one -> Gender.FEMALE
                R.id.mrb_two -> Gender.MALE
                else -> null
            }
            radioButtonText?.run {
                adoptPostViewModel.updateGenderSelection(this)
            }

            this@GenderBottomSeatFragment.dialog?.dismiss()
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
