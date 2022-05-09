package com.dev6.feed.view

import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.FeedViewType
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentHomeBinding
import com.dev6.feed.viewmodel.FeedViewModel


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private  val feedViewModel : FeedViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        feedViewModel.setCurrentView(FeedViewType.HOME)
    }
}