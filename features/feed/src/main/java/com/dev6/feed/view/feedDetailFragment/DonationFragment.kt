package com.dev6.feed.view.feedDetailFragment

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.PostOptionType
import com.dev6.feed.R
import com.dev6.feed.adapter.RecommendDonationAdapter
import com.dev6.feed.databinding.FragmentDonationBinding
import com.dev6.feed.option.BottomSheet
import timber.log.Timber


class DonationFragment : BindingFragment<FragmentDonationBinding>(R.layout.fragment_donation) {

    private lateinit var bottomSheet: BottomSheet
    private lateinit var donationRc: RecyclerView

    override fun initView() {
        donationRc = binding.donationRc
        donationRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecommendDonationAdapter{

            }
        }
    }

    override fun initListener() {
        binding.apply {
            donationOption1.setOnClickListener {
                makeBottomSheet(R.layout.fragment_bottom_sheet)
                bottomSheet.show(childFragmentManager, bottomSheet.tag)
            }

            donationOption2.setOnClickListener {
                makeBottomSheet(R.layout.fragment_category_sheet)
                bottomSheet.show(childFragmentManager, bottomSheet.tag)
            }
        }
    }

    private fun makeBottomSheet(type: Int) {
        bottomSheet = BottomSheet({
            sortFeed(it)
        }, type)
    }

    //아래 메소드에서 소팅 함수 and 리스트 변경 notify
    private fun sortFeed(type: PostOptionType) {
        when (type) {
            PostOptionType.FEED -> {

            }
            PostOptionType.THING -> {
                Timber.tag("sfsdf").v("피드")
            }
            PostOptionType.DATE -> {

            }
            PostOptionType.TOTAL -> {

            }
            PostOptionType.RECENT -> {

            }
        }
    }
}