package com.dev6.feed.view.profileDetailFragment

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.adapter.DailyPagingAdapter
import com.dev6.feed.databinding.FragmentProfileDailyBinding
import com.dev6.feed.view.feedDetailActivity.DailyFeedDetailActivity
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.collectLatest

class ProfileDailyFragment :
    BindingFragment<FragmentProfileDailyBinding>(R.layout.fragment_profile_daily) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var profileDailyRc: RecyclerView
    private lateinit var pagingAdapter: DailyPagingAdapter

    override fun initView() {
        super.initView()
        pagingAdapter = DailyPagingAdapter{
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
        feedViewModel.getFeedList("app1","")//likedUser는 이 프로필을 보는 userId
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
                        when (event.uiState) {
                            is UiState.Success -> {
                                event.uiState.data.collectLatest {
                                    pagingAdapter.submitData(it)
                                }
                            }
                            is UiState.Error -> {
                                Toast.makeText(context, "실패 했어여", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(context, "로딩 했어여", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }
}