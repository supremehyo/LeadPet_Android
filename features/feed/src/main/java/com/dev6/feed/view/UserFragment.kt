package com.dev6.feed.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentUserBinding
import com.dev6.feed.view.profileDetailFragment.ProfileUserDonationFragment
import com.dev6.feed.view.profileDetailFragment.ProfileUserScrapFragment
import com.dev6.feed.viewmodel.FeedViewModel
import com.google.android.material.tabs.TabLayout


class UserFragment : BindingFragment<FragmentUserBinding>(R.layout.fragment_user){

    private val feedViewModel: FeedViewModel by activityViewModels()
    lateinit var profileUserScrapFragment: ProfileUserScrapFragment
    lateinit var profileUserDonationFragment: ProfileUserDonationFragment

    lateinit var selected: Fragment

    override fun initView() {
        profileUserScrapFragment = ProfileUserScrapFragment()
        profileUserDonationFragment = ProfileUserDonationFragment()
    }

    override fun initViewModel() {
        //TODO 해당 유저의 uuid 로 관련 피드 데이터 요청
    }

    override fun initListener() {
        //TODO 해당 피드를 눌렀을때 post id 를 가지고 있다가 이동
    }

    private fun initTabLayout() {
        binding.apply {
            userProfileTabs.run {
                addTab(newTab().setText("기부"))
                addTab(newTab().setText("저장"))
            }

            userProfileTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            selected = profileUserDonationFragment
                        }
                        1 -> {
                            selected = profileUserScrapFragment
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