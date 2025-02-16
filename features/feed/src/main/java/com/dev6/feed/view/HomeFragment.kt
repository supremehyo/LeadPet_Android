package com.dev6.feed.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.core.enums.UserType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.adapter.RecommendAdoptAdapter
import com.dev6.feed.adapter.RecommendDonationAdapter
import com.dev6.feed.adapter.RecommendFeedAdapter
import com.dev6.feed.view.feedDetailActivity.AdoptFeedDetailActivity
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
        feedViewModel.setCurrentView(FeedViewType.HOME)
        dailyPagingAdapter = RecommendFeedAdapter {
            Timber.d(it.toString())
            if (UserData.userType == UserType.NORMAL) {
                feedViewModel.setCurrentView(FeedViewType.FEEDDETAIL)
                findNavController().navigate(
                    R.id.action_homeFragment_to_fragmentFeedDaily2,
                    Bundle().apply { putSerializable("dailyPost", it) }
                )
            } else {
                feedViewModel.setCurrentView(FeedViewType.FEEDDETAIL)
                findNavController().navigate(
                    R.id.action_homeFragment_to_fragmentFeedDaily,
                    Bundle().apply { putSerializable("dailyPost", it) }
                )
            }
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

        feedViewModel.getDonationList("", "")
        feedViewModel.getAdoptList("")
        feedViewModel.getFeedList("", "")

        feedViewModel.setCurrentView(FeedViewType.HOME)
    }

    private fun initRc() {
        binding.recommendDonationRc.apply {
            adapter = donationPagingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rvRecommendFeed.apply {
            adapter = dailyPagingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvHelp.apply {
            adapter = adoptPagingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
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
                    }
                    else -> {
                    }
                }
            }
        }
        repeatOnStarted {
            feedViewModel.eventDailyList.collect { event ->
                when (event) {
                    is FeedViewModel.Event.DailyUiEvent -> {
                        dailyPagingAdapter.submitData(event.pagingData)
                    }
                    else -> {
                    }
                }
            }
        }
    }
}
