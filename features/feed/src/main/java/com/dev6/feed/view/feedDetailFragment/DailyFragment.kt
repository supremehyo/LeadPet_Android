package com.dev6.feed.view.feedDetailFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.data.entity.DailyFeedEntitiy
import com.dev6.feed.R
import com.dev6.feed.adapter.DailyshelterRecyclerAdapter
import com.dev6.feed.adapter.PagingAdapter
import com.dev6.feed.databinding.FragmentDailyBinding
import com.dev6.feed.databinding.FragmentFeedBinding
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DailyFragment : BindingFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    private  val feedViewModel : FeedViewModel by activityViewModels()
    private lateinit var dailyRc: RecyclerView
    private lateinit var dailyNearShelterRc : RecyclerView
    private lateinit var pagingAdapter : PagingAdapter

    override fun initView() {
        super.initView()

        dailyRc = binding.dailyRc
        pagingAdapter = PagingAdapter()

        dailyNearShelterRc = binding.dailyNearShelterRc
        dailyNearShelterRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DailyshelterRecyclerAdapter{

            }
        }

        dailyRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pagingAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewLifecycleOwner.lifecycleScope.launch {
            feedViewModel.getFeedList().collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }
}