package com.dev6.feed.view

import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.FeedViewType
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentProfileBinding
import com.dev6.feed.viewmodel.FeedViewModel


class ProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private  val feedViewModel : FeedViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        feedViewModel.setCurrentView(FeedViewType.PROFILE)
    }
}