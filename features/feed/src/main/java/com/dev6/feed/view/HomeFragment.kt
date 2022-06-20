package com.dev6.feed.view

import android.content.Intent
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.FeedViewType
import com.dev6.feed.R
import com.dev6.feed.adapter.RecommendAdoptAdapter
import com.dev6.feed.adapter.RecommendDonationAdapter
import com.dev6.feed.adapter.RecommendFeedAdapter
import com.dev6.feed.view.feedDetailActivity.DailyFeedDetailActivity
import com.dev6.feed.viewmodel.FeedViewModel
import timber.log.Timber


class HomeFragment : BindingFragment<com.dev6.feed.databinding.FragmentHomeBinding>(R.layout.fragment_home) {

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

            }.also {
                it.submitList(listOf("첫번째" , "첫번째" , "첫번째" ,"첫번째" ,"첫번째" ,"첫번째"))
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.recommendAdoptRc.apply {
            adapter = RecommendAdoptAdapter{

            }.also {
                it.submitList(listOf("첫번째" , "첫번째" , "첫번째" ,"첫번째" ,"첫번째" ,"첫번째"))
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.recommendFeedRc.apply {
            adapter = RecommendFeedAdapter{
                Timber.d(it.toString())
                val joinIntent = Intent(context, DailyFeedDetailActivity::class.java)
                startActivity(joinIntent)
            }.also {
                it.submitList(listOf("첫번째" , "첫번째" , "첫번째" ,"첫번째" ,"첫번째" ,"첫번째"))
            }

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}