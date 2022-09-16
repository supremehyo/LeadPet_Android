package com.dev6.feed.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.FeedViewType
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentFeedBinding
import com.dev6.feed.view.feedDetailFragment.AdoptFragment
import com.dev6.feed.view.feedDetailFragment.DailyFragment
import com.dev6.feed.view.feedDetailFragment.DonationFragment
import com.dev6.feed.viewmodel.FeedViewModel
import com.google.android.material.tabs.TabLayout

class FeedFragment : BindingFragment<FragmentFeedBinding>(R.layout.fragment_feed) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    lateinit var navHostFragment: Fragment

    val dailyFragment: DailyFragment by lazy { DailyFragment() }
    val donationFragment: DonationFragment by lazy { DonationFragment() }
    val adoptFragment: AdoptFragment by lazy { AdoptFragment() }
    lateinit var selected: Fragment

    override fun initView() {
        super.initView()
        initTabLayout()

        // 초기화면
        childFragmentManager.beginTransaction().replace(R.id.nav_detail_fragment, dailyFragment).commit()
        feedViewModel.setCurrentView(FeedViewType.TOTAL)
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    private fun initTabLayout() {
        binding.apply {
            mainTabs.run {
                addTab(newTab().setText("일상"))
                addTab(newTab().setText("기부"))
                addTab(newTab().setText("입양"))
            }

            mainTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // 눌렀을때 색 변경이랑 네이게이션 이동 코드
                    when (tab?.position) {
                        0 -> {
                            selected = dailyFragment
                        }
                        1 -> {
                            selected = donationFragment
                        }
                        2 -> {
                            selected = adoptFragment
                        }
                    }
                    childFragmentManager.beginTransaction().replace(R.id.nav_detail_fragment, selected).commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }
}
