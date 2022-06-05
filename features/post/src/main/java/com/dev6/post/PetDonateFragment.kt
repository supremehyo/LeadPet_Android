package com.dev6.post

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.dev6.core.base.BindingFragment
import com.dev6.post.databinding.FragmentPetDonateBinding
import com.skydoves.balloon.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetDonateFragment : BindingFragment<FragmentPetDonateBinding>(R.layout.fragment_pet_donate) {
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DonateDialogFragment().show(this@PetDonateFragment.parentFragmentManager, "logout")
    }
    
    override fun initListener() {
        super.initListener()
        binding.ivInfo.setOnClickListener {
            val balloon = createBalloon(requireContext()) {
                setWidth(BalloonSizeSpec.WRAP)
                setHeight(BalloonSizeSpec.WRAP)
                setText("기부 방법 선택이 가능합니다. \n" +
                            "입력하신 정보가 없을 시 ‘마이페이\n지’로 이동해 작성 할 수 있습니다."
                )
                setTextColorResource(R.color.white)
                setTextSize(12f)
                setTextGravity(Gravity.START)
                setIconDrawableResource(R.drawable.ic_close_thin)
                setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
                setArrowSize(10)
                setArrowPosition(0.5f)
                setPadding(10)
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
    }
}