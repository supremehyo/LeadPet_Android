package com.dev6.feed.view.feedDetailActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingActivity
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ActivityDonationFeedDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DonationFeedDetailActivity :
    BindingActivity<ActivityDonationFeedDetailBinding>(R.layout.activity_donation_feed_detail) {

    lateinit var currentFeed: DonationPostFeed

    override fun initView() {
        super.initView()
        currentFeed = intent.getSerializableExtra("donationPostFeed") as DonationPostFeed
        makeCurrentView()
    }

    override fun initViewModel() {

    }

    override fun initListener() {

    }

    private fun makeCurrentView() {
        binding.apply {
            currentFeed.apply {
                donationFeedUserId.text = userId
                donationFeedTitle.text = title
                donationFeedContent.text = contents
                donationFeedEndDate.text = endDate[0] + "." + endDate[1]
                donationTag.text = "물"
                makeImageView(images[0])
            }
        }
    }

    private fun makeImageView(uri: String) {
        Glide.with(this).load(uri).error(R.drawable.alarm).into(binding.dailyContentImage)
    }
}