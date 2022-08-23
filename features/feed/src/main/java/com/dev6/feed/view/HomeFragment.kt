package com.dev6.feed.view

import android.content.Intent
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.adapter.RecommendAdoptAdapter
import com.dev6.feed.adapter.RecommendDonationAdapter
import com.dev6.feed.adapter.RecommendFeedAdapter
import com.dev6.feed.view.feedDetailActivity.AdoptFeedDetailActivity
import com.dev6.feed.view.feedDetailActivity.DailyFeedDetailActivity
import com.dev6.feed.view.feedDetailActivity.DonationFeedDetailActivity
import com.dev6.feed.viewmodel.FeedViewModel
import timber.log.Timber

class HomeFragment :
    BindingFragment<com.dev6.feed.databinding.FragmentHomeBinding>(R.layout.fragment_home) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var dailyPagingAdapter: RecommendFeedAdapter
    private lateinit var adoptPagingAdapter: RecommendAdoptAdapter
    private lateinit var donationPagingAdapter: RecommendDonationAdapter
    var userId = ""
    override fun initView() {
        super.initView()

        dailyPagingAdapter = RecommendFeedAdapter {
            Timber.d(it.toString())
            val dailyIntent = Intent(context, DailyFeedDetailActivity::class.java)
            dailyIntent.putExtra("dailyPostFeed", it)
            startActivity(dailyIntent)
        }
        adoptPagingAdapter = RecommendAdoptAdapter {
            val adoptIntent = Intent(context, AdoptFeedDetailActivity::class.java)
            adoptIntent.putExtra("adoptPostFeed", it)
            startActivity(adoptIntent)
        }
        donationPagingAdapter = RecommendDonationAdapter {
            val donationIntent = Intent(context, DonationFeedDetailActivity::class.java)
            donationIntent.putExtra("donationPostFeed", it)
            startActivity(donationIntent)
        }

        initRc()
    }

    override fun initViewModel() {
        super.initViewModel()

        feedViewModel.getDonationList("")
        feedViewModel.getAdoptList("")
        feedViewModel.getFeedList(userId, "")

        feedViewModel.setCurrentView(FeedViewType.HOME)

    }

    private fun initRc() {

        binding.recommendDonationRc.apply {
            adapter = donationPagingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.recommendFeedRc.apply {
            adapter = dailyPagingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.recommendAdoptRc.apply {
            adapter = adoptPagingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

    }



    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this.context, id)
        newConstraintSet.applyTo(binding.clHome)
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(binding.clHome, transition)
    }

    override fun afterViewCreated() {

        repeatOnStarted {
            feedViewModel.eventDonationList.collect { event ->
                when (event) {
                    is FeedViewModel.Event.DonationUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                event.uiState.data.collect {
                                    donationPagingAdapter.submitData(it)
                                }
                            }
                            is UiState.Error -> {
                                Toast.makeText(context, "실패 했어여", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(context, "로딩 했어여", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                    else -> {

                    }
                }
            }
        }
            repeatOnStarted {
                feedViewModel.eventAdoptList.collect { event ->
                    when (event) {
                        is FeedViewModel.Event.AdoptUiEvent -> {
                            when (event.uiState) {
                                is UiState.Success -> {
                                    event.uiState.data.collect {
                                        adoptPagingAdapter.submitData(it)
                                    }
                                }
                                is UiState.Error -> {
                                    Toast.makeText(context, "실패 했어여", Toast.LENGTH_SHORT).show()
                                }
                                is UiState.Loding -> {
                                    Toast.makeText(context, "로딩 했어여", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else->{

                    }
                    }
                }
            }
            repeatOnStarted {
                feedViewModel.eventDailyList.collect { event ->
                    when (event) {
                        is FeedViewModel.Event.DailyUiEvent -> {
                            when (event.uiState) {
                                is UiState.Success -> {
                                    event.uiState.data.collect {
                                        dailyPagingAdapter.submitData(it)
                                    }
                                }
                                is UiState.Error -> {
                                    Toast.makeText(context, "실패 했어여", Toast.LENGTH_SHORT).show()
                                }
                                is UiState.Loding -> {
                                    Toast.makeText(context, "로딩 했어여", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else->{

                    }
                    }
                }
            }

        }


    }
