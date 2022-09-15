package com.dev6.feed.view.feedDetailActivity

import android.graphics.Color
import android.os.Build
import android.service.autofill.UserData
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingActivity
import com.dev6.core.base.UiState
import com.dev6.core.enums.PostType
import com.dev6.core.util.extension.fewDay
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.daily.DailyPost
import com.dev6.feed.R
import com.dev6.feed.adapter.comment.DailyCommentAdapter
import com.dev6.feed.databinding.ActivityDailyFeedDetailBinding
import com.dev6.feed.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFeedDetailActivity :
    BindingActivity<ActivityDailyFeedDetailBinding>(R.layout.activity_daily_feed_detail) {

    private val feedViewModel: FeedViewModel by viewModels()
    lateinit var currentFeed: DailyPost
    private lateinit var dailycommentRc: RecyclerView
    private lateinit var commentAdapter: DailyCommentAdapter
    var likedBoolean = false

    override fun initView() {
        super.initView()
        currentFeed = intent.getSerializableExtra("dailyPostFeed") as DailyPost
        makeCurrentView()
        commentAdapter = DailyCommentAdapter {
//            if (com.dev6.core.UserData.userType == "NORMAL") {
//                //     NavHostFragment.findNavController(fragment = this).navigate(R.id.action_feedFragment_to_userFragment)
//            } else {
//                //   NavHostFragment.findNavController().navigate(R.id.action_feedFragment_to_profileFragment)
//            }
        }
        dailycommentRc = binding.dailyCommentRv
        dailycommentRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        feedViewModel.getCommentListByPostId(currentFeed.normalPostId)
    }

    override fun initListener() {
        super.initListener()
        binding.dailyLikeImage.setOnClickListener {
            feedViewModel.postLike(currentFeed.normalPostId, "uidkko149")
        }

        binding.dailyLikeImage.setOnClickListener {
            feedViewModel.postLike(currentFeed.normalPostId, "uidkko149")
        }
        binding.cbBookmark.setOnClickListener {
            if (!binding.cbBookmark.isChecked) {
                feedViewModel.executeBookMark(
                    currentFeed.normalPostId,
                    PostType.NORMAL_POST,
                    com.dev6.core.UserData.userId
                )
            } else {
                feedViewModel.executeUnBookMark(
                    currentFeed.normalPostId,
                    com.dev6.core.UserData.userId
                )
            }
        }
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        repeatOnStarted {
            feedViewModel.eventFlowComment.collect { event ->
                when (event) {
                    is FeedViewModel.Event.CommentUiEvnet -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                event.uiState.data.collect {
                                    commentAdapter.submitData(it)
                                }
                            }
                            is UiState.Error -> {
                                Toast.makeText(
                                    this@DailyFeedDetailActivity,
                                    "실패 했어여",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(
                                    this@DailyFeedDetailActivity,
                                    "로딩 했어여",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    else -> {
                    }
                }
            }
        }

        repeatOnStarted {
            feedViewModel.eventPostLike.collect { event ->
                when (event) {
                    is FeedViewModel.Event.CommentLikeUiEvnet -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                Toast.makeText(
                                    this@DailyFeedDetailActivity,
                                    "좋아요 성공",
                                    Toast.LENGTH_SHORT
                                ).show()
                                changeLikedHeart()
                            }
                            is UiState.Error -> {
                                Toast.makeText(
                                    this@DailyFeedDetailActivity,
                                    "실패 했어여",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(
                                    this@DailyFeedDetailActivity,
                                    "로딩 했어여",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun makeCurrentView() {
        binding.apply {
            currentFeed.apply {
                dailyFeedTitleTv.text = title
                dailyFeedContentTv.text = contents
                dailyLikeCount.text = likedCount.toString()
                dailyCommentCount.text = commentCount.toString()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    dailyFeedTimeTv.text =
                        fewDay(
                            createdDate[0],
                            createdDate[1],
                            createdDate[2],
                            createdDate[3],
                            createdDate[4],
                            createdDate[5]
                        )
                }
                Log.v("initlike", likedBoolean.toString())
                likedBoolean = liked
                makeLikedHeart(liked)
                makeImageView("")
            }
        }
    }

    private fun makeImageView(uri: String) {
        Glide.with(this).load(uri).error(R.drawable.alarm).into(binding.dailyContentImage)
    }

    private fun makeLikedHeart(boolean: Boolean) {
        if (boolean) {
            binding.dailyLikeImage.setColorFilter(Color.parseColor("#FF675E"))
        } else {
            binding.dailyLikeImage.setColorFilter(Color.parseColor("#C4C4C4"))
        }
    }

    private fun changeLikedHeart() {
        if (likedBoolean) {
            Log.v("initlike2", likedBoolean.toString())
            binding.dailyLikeImage.setColorFilter(Color.parseColor("#C4C4C4"))
            binding.dailyLikeCount.text = (binding.dailyLikeCount.text.toString().toInt() + 1).toString()
            likedBoolean = false
        } else {
            Log.v("initlike3", likedBoolean.toString())
            binding.dailyLikeImage.setColorFilter(Color.parseColor("#FF675E"))
            binding.dailyLikeCount.text = (binding.dailyLikeCount.text.toString().toInt() - 1).toString()
            likedBoolean = true
        }
    }
}
