package com.dev6.feed.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.FeedViewType
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentProfileBinding
import com.dev6.feed.view.profileDetailFragment.ProfileAdoptFragment
import com.dev6.feed.view.profileDetailFragment.ProfileDailyFragment
import com.dev6.feed.view.profileDetailFragment.ProfileDonationFragment
import com.dev6.feed.view.profileDetailFragment.ProfileIntroduceFragment
import com.dev6.feed.viewmodel.FeedViewModel
import com.google.android.material.tabs.TabLayout


class ShelterProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val feedViewModel: FeedViewModel by activityViewModels()

    lateinit var profileIntroduceFragment: ProfileIntroduceFragment
    lateinit var profileDailyFragment: ProfileDailyFragment
    lateinit var profileDonationFragment: ProfileDonationFragment
    lateinit var profileAdoptFragment: ProfileAdoptFragment
    lateinit var selected: Fragment

    override fun initView() {
        super.initView()
        initTabLayout()
        feedViewModel.setCurrentView(FeedViewType.PROFILE)

        profileIntroduceFragment = ProfileIntroduceFragment()
        profileDailyFragment = ProfileDailyFragment()
        profileDonationFragment = ProfileDonationFragment()
        profileAdoptFragment = ProfileAdoptFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, profileIntroduceFragment).commit()
    }

    private fun initTabLayout() {
        binding.apply {
            profileTabs.run {
                addTab(newTab().setText("소개"))
                addTab(newTab().setText("일상"))
                addTab(newTab().setText("기부"))
                addTab(newTab().setText("입양"))
            }

            profileTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {

                    // 눌렀을때 색 변경이랑 네이게이션 이동 코드
                    when (tab?.position) {
                        0 -> {
                            selected = profileIntroduceFragment
                            //feedViewModel.setCurrentView(FeedViewType.TOTAL)
                        }
                        1 -> {
                            selected = profileDailyFragment
                            //feedViewModel.setCurrentView(FeedViewType.DAILY)
                        }
                        2 -> {
                            selected = profileDonationFragment
                            //feedViewModel.setCurrentView(FeedViewType.DONATION)
                        }
                        3 -> {
                            selected = profileAdoptFragment

                        }
                    }
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, selected).commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }
    }
}