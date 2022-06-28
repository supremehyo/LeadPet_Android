package com.dev6.feed.view.feedDetailFragment

import android.view.ViewGroup
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentDonationBinding
import com.dev6.feed.option.BottomSheet


class DonationFragment : BindingFragment<FragmentDonationBinding>(R.layout.fragment_donation) {

    private lateinit var bottomSheet: BottomSheet

    override fun initView() {

    }

    override fun initListener() {
        binding.apply {
            donationOption1.setOnClickListener {
                bottomSheet.show(childFragmentManager, bottomSheet.tag).also {
                    BottomSheet({
                        when (it) {

                        }
                    }, "정렬")
                }
            }
            donationOption2.setOnClickListener {
                bottomSheet.show(childFragmentManager, bottomSheet.tag).also {
                    BottomSheet({
                        when (it) {

                        }
                    }, "정")
                }
            }
        }
    }

}