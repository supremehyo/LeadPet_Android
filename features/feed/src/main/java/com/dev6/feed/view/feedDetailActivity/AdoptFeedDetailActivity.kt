package com.dev6.feed.view.feedDetailActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingActivity
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ActivityAdoptFeedDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdoptFeedDetailActivity : BindingActivity<ActivityAdoptFeedDetailBinding>(R.layout.activity_adopt_feed_detail) {

    lateinit var currentFeed : AdoptPostFeed

    override fun initView() {
        super.initView()
        currentFeed = intent.getSerializableExtra("adoptPostFeed") as AdoptPostFeed
        makeCurrentView()
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
                AdoptFeedContent.text = contents
                adoptFeedAnimalType.text = animalType+" "+species
                adoptFeedAge.text = "10"
                adoptFeedDate.text = "$startDate~$endDate"
                adoptFeedDisease.text = "질병"
                adoptFeedTitle.text = title
                adoptFeedUserId.text = userId
                makeImageView("")
            }
        }
    }

    private fun makeImageView(uri: String) {
        Glide.with(this).load(uri).error(R.drawable.alarm).into(binding.adoptContentImage)
    }
}