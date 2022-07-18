package com.dev6.feed.view.feedDetailActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingActivity
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.feed.R
import com.dev6.feed.adapter.comment.DailyCommentAdapter
import com.dev6.feed.databinding.ActivityDailyFeedDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFeedDetailActivity :
    BindingActivity<ActivityDailyFeedDetailBinding>(R.layout.activity_daily_feed_detail) {

    lateinit var currentFeed : DailyPostFeed

    private lateinit var dailycommentRc: RecyclerView
    override fun initView() {
        super.initView()
        currentFeed = intent.getSerializableExtra("dailyPostFeed") as DailyPostFeed
        makeCurrentView()

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

    private fun makeCurrentView() {
        binding.apply {
            currentFeed.apply {
                dailyFeedTitleTv.text = title
                dailyFeedContentTv.text = contents

                makeImageView(images[0])
            }
        }
    }

    private fun makeImageView(uri: String) {
        Glide.with(this).load(uri).error(R.drawable.alarm).into(binding.dailyContentImage)
    }
}