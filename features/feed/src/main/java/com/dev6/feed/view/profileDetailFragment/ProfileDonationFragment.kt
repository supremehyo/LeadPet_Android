package com.dev6.feed.view.profileDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.adapter.RecommendDonationAdapter
import com.dev6.feed.databinding.FragmentProfileDonationBinding


class ProfileDonationFragment :
    BindingFragment<FragmentProfileDonationBinding>(R.layout.fragment_profile_donation) {

    private lateinit var donationRc: RecyclerView

    override fun initView() {
        super.initView()
        donationRc = binding.profileDonationRc
        donationRc.apply {
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