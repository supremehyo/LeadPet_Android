package com.dev6.feed.view.feedDetailActivity

import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingActivity
import com.dev6.core.enums.PostType
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ActivityAdoptFeedDetailBinding
import com.dev6.feed.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdoptFeedDetailActivity :
    BindingActivity<ActivityAdoptFeedDetailBinding>(R.layout.activity_adopt_feed_detail) {

    lateinit var currentFeed: AdoptPostFeed
    private val feedViewModel: FeedViewModel by viewModels()

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
        binding.cbBookmark.setOnClickListener {
            if (binding.cbBookmark.isChecked) {
                feedViewModel.executeBookMark(
                    currentFeed.adoptionPostId,
                    PostType.DONATION_POST,
                    com.dev6.core.UserData.userId
                )
            } else {
                feedViewModel.executeUnBookMark(
                    currentFeed.adoptionPostId,
                    com.dev6.core.UserData.userId
                )
            }
        }
    }

    private fun makeCurrentView() {
        binding.apply {
            currentFeed.apply {
                AdoptFeedContent.text = contents
                adoptFeedAnimalType.text = animalType.item + " " + species
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
