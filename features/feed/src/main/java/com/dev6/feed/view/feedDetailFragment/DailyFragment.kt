package com.dev6.feed.view.feedDetailFragment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.adapter.DailyPagingAdapter
import com.dev6.feed.adapter.DailyshelterRecyclerAdapter
import com.dev6.feed.databinding.FragmentDailyBinding
import com.dev6.feed.view.feedDetailActivity.DailyFeedDetailActivity
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.collectLatest

class DailyFragment : BindingFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var dailyRc: RecyclerView
    private lateinit var dailyNearShelterRc: RecyclerView
    private lateinit var pagingAdapter: DailyPagingAdapter
    private lateinit var shelterAdapter: DailyshelterRecyclerAdapter
    lateinit var navController: NavController
    var userId = ""

    override fun initView() {
        super.initView()

        dailyRc = binding.dailyRc
        dailyNearShelterRc = binding.dailyNearShelterRc

        pagingAdapter = DailyPagingAdapter {
            if(UserData.userType == "NORMAL"){
                feedViewModel.setCurrentView(FeedViewType.FEEDDETAIL)
                findNavController().navigate(R.id.action_feedFragment_to_fragmentFeedDaily , Bundle().apply {putSerializable("dailyPost", it)})
            }else{
                feedViewModel.setCurrentView(FeedViewType.FEEDDETAIL)
                findNavController().navigate(R.id.action_feedFragment_to_fragmentFeedDaily2 ,Bundle().apply {putSerializable("shelterData", it)})
            }
        }

        shelterAdapter = DailyshelterRecyclerAdapter {
            if(UserData.userType == "NORMAL"){
                feedViewModel.setCurrentView(FeedViewType.SHELTERPROFILECLICK)
                findNavController().navigate(R.id.action_feedFragment_to_profileFragment2 , Bundle().apply {putSerializable("shelterData", it)})
            }else{
                feedViewModel.setCurrentView(FeedViewType.SHELTERPROFILECLICK)
                findNavController().navigate(R.id.action_feedFragment_to_profileFragment ,Bundle().apply {putSerializable("shelterData", it)})
            }
        }


        dailyNearShelterRc.apply {
            adapter = shelterAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        dailyRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pagingAdapter
        }
    }

    override fun initViewModel() {
        getDailyList()
        getShelterList()
    }

    private fun getDailyList() {
        feedViewModel.getFeedList("","")
    }

    private fun getShelterList() {
        feedViewModel.getNearShelterList("", "")
    }


    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            feedViewModel.eventDailyList.collect { event ->
                when (event) {
                    is FeedViewModel.Event.DailyUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                event.uiState.data.collectLatest {
                                    pagingAdapter.submitData(it)
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
            feedViewModel.eventShelterList.collect { event ->
                when (event) {
                    is FeedViewModel.Event.ShelterUiEvnet -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                event.uiState.data.collectLatest {
                                    shelterAdapter.submitData(it)
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
    }
}