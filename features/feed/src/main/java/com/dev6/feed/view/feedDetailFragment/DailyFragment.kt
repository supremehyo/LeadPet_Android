package com.dev6.feed.view.feedDetailFragment

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.domain.util.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.adapter.DailyshelterRecyclerAdapter
import com.dev6.feed.adapter.DailyPagingAdapter
import com.dev6.feed.databinding.FragmentDailyBinding
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class DailyFragment : BindingFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var dailyRc: RecyclerView
    private lateinit var dailyNearShelterRc: RecyclerView
    private lateinit var pagingAdapter: DailyPagingAdapter

    override fun initView() {
        super.initView()

        dailyRc = binding.dailyRc
        pagingAdapter = DailyPagingAdapter()



        dailyNearShelterRc = binding.dailyNearShelterRc
        dailyNearShelterRc.apply {
            adapter = pagingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        dailyRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pagingAdapter
        }
    }

    override fun initViewModel() {

    }

    private fun getDailyList() {
        feedViewModel.getFeedList()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        getDailyList()
        repeatOnStarted {
            feedViewModel.eventFlow.collect { event ->
                when (event) {
                    is FeedViewModel.Event.DailyUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                Toast.makeText(context, "성공 했어여", Toast.LENGTH_SHORT).show()
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
                }
            }
        }
    }
}