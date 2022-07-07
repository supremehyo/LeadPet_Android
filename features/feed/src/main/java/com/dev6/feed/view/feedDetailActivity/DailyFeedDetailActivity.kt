package com.dev6.feed.view.feedDetailActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingActivity
import com.dev6.feed.R
import com.dev6.feed.adapter.comment.DailyCommentAdapter
import com.dev6.feed.databinding.ActivityDailyFeedDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFeedDetailActivity :
    BindingActivity<ActivityDailyFeedDetailBinding>(R.layout.activity_daily_feed_detail) {

    private lateinit var dailycommentRc: RecyclerView
    override fun initView() {
        super.initView()
        dailycommentRc = binding.dailyCommentRv
        dailycommentRc.apply {
            adapter = DailyCommentAdapter {

            }.also {
                it.submitList(listOf("첫번째", "첫번째", "첫번째", "첫번째", "첫번째", "첫번째"))
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }
}