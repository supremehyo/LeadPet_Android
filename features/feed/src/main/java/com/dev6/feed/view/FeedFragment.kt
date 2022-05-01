package com.dev6.feed.view

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.adapter.PagingAdapter
import com.dev6.feed.databinding.FragmentFeedBinding
import com.dev6.feed.view.feedDetailFragment.AdoptFragment
import com.dev6.feed.view.feedDetailFragment.DailyFragment
import com.dev6.feed.view.feedDetailFragment.DonationFragment
import com.dev6.feed.view.feedDetailFragment.TotalFragment
import com.dev6.feed.viewmodel.FeedViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FeedFragment : BindingFragment<FragmentFeedBinding>(R.layout.fragment_feed) {

    private  val feedViewModel : FeedViewModel by activityViewModels()
    lateinit var  navHostFragment : Fragment
    var list  = listOf("경기도 성남시" , "서울특별시")

    lateinit var totalFragment: TotalFragment
    lateinit var dailyFragment: DailyFragment
    lateinit var donationFragment: DonationFragment
    lateinit var adoptFragment: AdoptFragment
    lateinit var selected : Fragment

    override fun initView() {
        super.initView()
        initTabLayout()

        totalFragment = TotalFragment()
        dailyFragment = DailyFragment()
        donationFragment = DonationFragment()
        adoptFragment = AdoptFragment()

        //초기화면
        childFragmentManager.beginTransaction().replace(R.id.nav_detail_fragment,totalFragment).commit()

    }

    override fun initViewModel() {
        super.initViewModel()
        binding.viewmodel = feedViewModel
        feedViewModel.setSpinnerEntry(list)
        lifecycleScope.launch {4
            feedViewModel.spinnerData.collect{
                Log.v("sdfsdfsdf" , it)
            }
        }

    }

    private fun initTabLayout(){
        binding.apply {
            mainTabs.run {
                addTab(newTab().setText("전체"))
                addTab(newTab().setText("일상"))
                addTab(newTab().setText("기부"))
                addTab(newTab().setText("입양"))
            }

            mainTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {

                    // 눌렀을때 색 변경이랑 네이게이션 이동 코드
                    when(tab?.position){
                        0 -> {
                            selected = totalFragment
                            // navHostFragment.findNavController().navigate(R.id.action_global_totalFragment)
                        }
                        1 -> {
                            selected = dailyFragment
                            //navHostFragment.findNavController().navigate(R.id.action_global_dailyFragment)
                        }
                        2 -> {
                            selected = donationFragment
                            // navHostFragment.findNavController().navigate(R.id.action_global_donationFragment)
                        }
                        3 -> {
                            selected = adoptFragment
                            //navHostFragment.findNavController().navigate(R.id.action_global_adoptFragment)
                        }
                    }
                    childFragmentManager.beginTransaction().replace(R.id.nav_detail_fragment,selected).commit()
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }
    }


}