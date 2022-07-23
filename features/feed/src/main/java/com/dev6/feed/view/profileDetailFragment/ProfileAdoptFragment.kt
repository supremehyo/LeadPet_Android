package com.dev6.feed.view.profileDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.adapter.RecommendDonationAdapter
import com.dev6.feed.databinding.FragmentProfileAdoptBinding
import com.dev6.feed.viewmodel.FeedViewModel


class ProfileAdoptFragment :
    BindingFragment<FragmentProfileAdoptBinding>(R.layout.fragment_profile_adopt) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var profileAdoptRc: RecyclerView

    override fun initView() {
        super.initView()
        profileAdoptRc = binding.profileAdoptRc
        profileAdoptRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecommendDonationAdapter{

            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }
}