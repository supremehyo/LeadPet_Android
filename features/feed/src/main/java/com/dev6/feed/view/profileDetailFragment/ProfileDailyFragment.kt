package com.dev6.feed.view.profileDetailFragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.adapter.DailyPagingAdapter
import com.dev6.feed.databinding.FragmentProfileDailyBinding

class ProfileDailyFragment :
    BindingFragment<FragmentProfileDailyBinding>(R.layout.fragment_profile_daily) {

    private lateinit var profileDailyRc: RecyclerView
    private lateinit var pagingAdapter: DailyPagingAdapter

    override fun initView() {
        super.initView()
        pagingAdapter = DailyPagingAdapter{

        }
        profileDailyRc = binding.profileDailyRc
        profileDailyRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pagingAdapter
        }.also {

        }
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }
}