package com.dev6.feed.view.profileDetailFragment

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.adapter.DailyPagingAdapter
import com.dev6.feed.databinding.FragmentProfileDailyBinding
import com.dev6.feed.view.feedDetailActivity.DailyFeedDetailActivity
import com.dev6.feed.viewmodel.FeedViewModel

class ProfileDailyFragment :
    BindingFragment<FragmentProfileDailyBinding>(R.layout.fragment_profile_daily) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var profileDailyRc: RecyclerView
    private lateinit var pagingAdapter: DailyPagingAdapter
    var userId: String = ""
    override fun initView() {
        super.initView()
        userId = arguments?.getString("userId") ?: ""
        pagingAdapter = DailyPagingAdapter {
            val dailyIntent = Intent(context, DailyFeedDetailActivity::class.java)
            dailyIntent.putExtra("dailyPostFeed", it)
            startActivity(dailyIntent)
        }

        profileDailyRc = binding.profileDailyRc
        profileDailyRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pagingAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        feedViewModel.getFeedList(userId, UserData.userId) // likedUser는 이 프로필을 보는 userId
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            feedViewModel.eventDailyList.collect { event ->
                when (event) {
                    is FeedViewModel.Event.DailyUiEvent -> {
                        pagingAdapter.submitData(event.pagingData)
                    }
                    else -> {}
                }
            }
        }
    }
}
