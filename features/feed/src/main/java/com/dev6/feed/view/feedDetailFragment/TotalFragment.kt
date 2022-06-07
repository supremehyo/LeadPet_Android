package com.dev6.feed.view.feedDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.adapter.DailyshelterRecyclerAdapter
import com.dev6.feed.adapter.PagingAdapter
import com.dev6.feed.databinding.FragmentTotalBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TotalFragment : BindingFragment<FragmentTotalBinding>(R.layout.fragment_total) {

    private lateinit var pagingAdapter : PagingAdapter
    private lateinit var totalRc: RecyclerView

    override fun initView() {

        totalRc = binding.dailyRc
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
        viewLifecycleOwner.lifecycleScope.launch {
            feedViewModel.getFeedList().collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    override fun initListener() {

    }

    override fun afterViewCreated() {

    }
}