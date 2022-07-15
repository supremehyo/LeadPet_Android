package com.dev6.post.bottom

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BaseBottomSheetDialogFragment
import com.dev6.post.R
import com.dev6.post.databinding.FragmentBottomNumberBinding
import com.dev6.post.databinding.FragmentBottomSeatBinding
import com.dev6.post.viewmodel.AdoptPostViewModel
import dagger.hilt.android.AndroidEntryPoint

/// TODO 시간날때, BottomSeatFragment 통합시키기
@AndroidEntryPoint
class AgeBottomSeatFragment :
    BaseBottomSheetDialogFragment<FragmentBottomNumberBinding>(R.layout.fragment_bottom_number) {

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
        binding.include.tvTop.text = "나이"
        binding.include.tvRight.run {
            setTextColor(resources.getColor(R.color.Main, null))
            setText(R.string.text_complete)
        }

        with(binding.numberPicker) {
            maxValue = 2020
            minValue = 1930
            wrapSelectorWheel = false
        }
    }

    override fun initListener() {
        binding.include.tvRight.setOnClickListener {
            val ageNumber = if(binding.cbMissing.isChecked) "알수 없음" else binding.numberPicker.value.toString()
            adoptPostViewModel.updateAgeSelection(ageNumber)
            this@AgeBottomSeatFragment.dialog?.dismiss()
        }
        binding.include.tvLeft.setOnClickListener {
            this@AgeBottomSeatFragment.dialog?.dismiss()
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