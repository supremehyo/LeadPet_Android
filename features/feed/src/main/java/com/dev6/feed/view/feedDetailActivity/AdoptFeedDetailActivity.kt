package com.dev6.feed.view.feedDetailActivity

import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingActivity
import com.dev6.core.base.UiState
import com.dev6.core.enums.PostType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ActivityAdoptFeedDetailBinding
import com.dev6.feed.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

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
            if (!binding.cbBookmark.isChecked) {
                feedViewModel.executeUnBookMark(
                    currentFeed.adoptionPostId,
                    com.dev6.core.UserData.userId
                )
            } else {
                feedViewModel.executeBookMark(
                    currentFeed.adoptionPostId,
                    PostType.ADOPTION_POST,
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
                adoptFeedAge.text = age.toString()+"살"
                adoptFeedDate.text = "$startDate~$endDate"
                adoptFeedDisease.text = disease
                adoptFeedTitle.text = title
                adoptFeedUserId.text = userId
                if(images!=null && images.isNotEmpty()){
                    makeImageView(images[0])
                }else{
                    makeImageView("")
                }
            }
        }
    }

    private fun makeImageView(uri: String) {
        Glide.with(this).load(uri).error(R.mipmap.img_3).centerCrop().into(binding.adoptContentImage)
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        repeatOnStarted {
            feedViewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is FeedViewModel.Event.BookMarkUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                Timber.tag("bookmark").d("북마크 성공")
                            }
                            is UiState.Error -> {
                                Timber.tag("bookmark").d("북마크 실패 ${event.uiState.error}")
                            }
                            is UiState.Loding -> {
                                Timber.tag("bookmark").d("북마크 로딩")
                            }
                        }
                    }
                    is FeedViewModel.Event.UnBookMarkUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                Timber.tag("unbookmark").d("북마크 성공")
                            }
                            is UiState.Error -> {
                                Timber.tag("unbookmark").d("북마크 실패 ${event.uiState.error}")
                            }
                            is UiState.Loding -> {
                                Timber.tag("unbookmark").d("북마크 로딩")
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}
