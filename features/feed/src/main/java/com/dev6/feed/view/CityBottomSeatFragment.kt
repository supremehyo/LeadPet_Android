package com.dev6.feed.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BaseBottomSheetDialogFragment
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentCityBottomSeatBinding
import com.dev6.feed.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityBottomSeatFragment : BaseBottomSheetDialogFragment<FragmentCityBottomSeatBinding>(R.layout.fragment_city_bottom_seat){
    private val profileViewModel: ProfileViewModel by activityViewModels()


    override fun initView() {
        binding.include.tvLeft.background =
            resources.getDrawable(
                com.dev6.post.R.drawable.ic_close_thin_black,
                null
            )
        binding.include.tvTop.text = "주소변경"
        binding.include.tvRight.run {
            setTextColor(resources.getColor(com.dev6.post.R.color.Main, null))
            setText(com.dev6.post.R.string.text_complete)
        }
    }

    override fun initListener() {
        binding.include.tvRight.setOnClickListener {

            val radioButtonText = when (binding.rgGender.checkedRadioButtonId) {
                R.id.citybt1 -> binding.citybt1.text.toString()
                R.id.citybt2 -> binding.citybt2.text.toString()
                R.id.citybt3 -> binding.citybt3.text.toString()
                R.id.citybt4 -> binding.citybt4.text.toString()
                R.id.citybt5 -> binding.citybt5.text.toString()
                R.id.citybt6 -> binding.citybt6.text.toString()
                else -> null
            }
            radioButtonText?.run {
                profileViewModel.updateCitySelection(this)
            }

            this.dialog?.dismiss()
        }
        binding.include.tvLeft.setOnClickListener {
            this.dialog?.dismiss()
        }
    }
}