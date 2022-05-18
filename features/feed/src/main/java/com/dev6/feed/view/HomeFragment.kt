package com.dev6.feed.view

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.FeedViewType
import com.dev6.feed.R
import com.dev6.feed.adapter.RecommendDonationAdapter
import com.dev6.feed.adapter.RecommendFeedAdapter
import com.dev6.feed.databinding.FragmentHomeBinding
import com.dev6.feed.viewmodel.FeedViewModel


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private  val feedViewModel : FeedViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        initRc()

    }

    override fun initViewModel() {
        super.initViewModel()
        feedViewModel.setCurrentView(FeedViewType.HOME)
        feedViewModel.getRecommendDonationList()
        feedViewModel.getRecommendFeedList()
    }

    private fun initRc(){
        binding.recommendDonationRc.apply {
            adapter = RecommendDonationAdapter{

            }
            layoutManager = LinearLayoutManager(context)
        }

        binding.recommendFeedRc.apply {
            adapter = RecommendFeedAdapter{ }
            layoutManager = LinearLayoutManager(context)
        }
    }
}