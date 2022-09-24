package com.dev6.post

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.*
import androidx.fragment.app.Fragment
import com.dev6.core.base.BindingFragment
import com.dev6.post.databinding.FragmentPetDonateBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.skydoves.balloon.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class PetDonateFragment : BindingFragment<FragmentPetDonateBinding>(R.layout.fragment_pet_donate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DonateDialogFragment().show(this@PetDonateFragment.parentFragmentManager, "logout")
    }

    override fun initView() {
        super.initView()

        with(binding) {
            cvEndDate.setHint("종료일")
        }
    }

    override fun initListener() {
        super.initListener()
        binding.ivInfo.setOnClickListener {
            val text =
                SpannableString("기부 방법 선택이 가능합니다. \n 입력하신 정보가 없을 시 ‘마이페이\n지’로 이동해 작성 할 수 있습니다.")
            text.setSpan(StyleSpan(Typeface.BOLD), 32, 41, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val balloon = createBalloon(requireContext()) {
                setWidth(BalloonSizeSpec.WRAP)
                setHeight(BalloonSizeSpec.WRAP)
                setText(text)
                setTextColorResource(R.color.Gray_01)
                setTextSize(12f)
                setTextGravity(Gravity.START)
                setIconDrawableResource(R.drawable.ic_close_thin)
                setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
                setArrowSize(10)
                setArrowPosition(0.5f)
                setPadding(10)
                setPaddingLeft(16)
                setCornerRadius(8f)
                setIconGravity(IconGravity.END)
                setIconSpace(5)
                setBackgroundColorResource(R.color.Gray_07)
                setBalloonAnimation(BalloonAnimation.ELASTIC)
                setLifecycleOwner(viewLifecycleOwner)
                build()
            }
            balloon.showAlignBottom(binding.ivInfo)

            balloon.setOnBalloonClickListener {
                balloon.dismiss()
            }
        }

        binding.cvStartDate.setClick { excuteDatePicker() }
        binding.cvEndDate.setClick { excuteDatePicker() }

    }

    private fun excuteDatePicker() {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
                .setTitleText("날짜를 골라주세용 응애!!!")
                .build()

        dateRangePicker.addOnPositiveButtonClickListener {
            binding.cvStartDate.setText(simpleDateFormat.format(it.first))
            binding.cvEndDate.setText(simpleDateFormat.format(it.second))
        }
        dateRangePicker.show(this.parentFragmentManager, null)
    }

}