package com.dev6.feed.view.feedDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.adapter.AdoptAdapter
import com.dev6.feed.adapter.DailyshelterRecyclerAdapter
import com.dev6.feed.adapter.PagingAdapter
import com.dev6.feed.databinding.FragmentAdoptBinding
import com.dev6.feed.databinding.FragmentDailyBinding
import com.dev6.feed.viewmodel.FeedViewModel


class AdoptFragment : BindingFragment<FragmentAdoptBinding>(R.layout.fragment_adopt)  {

    private  val feedViewModel : FeedViewModel by activityViewModels()

    override fun initView() {
        binding.adoptRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AdoptAdapter{

            }
        }
    }

    override fun initViewModel() {
        feedViewModel.getAdoptList()
    }

    override fun initListener() {
        binding.adoptSpinner
    }

}