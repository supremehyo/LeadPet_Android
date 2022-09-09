package com.dev6.feed.view.profileDetailFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.adapter.AdoptPagingAdapter
import com.dev6.feed.adapter.RecommendDonationAdapter
import com.dev6.feed.databinding.FragmentProfileAdoptBinding
import com.dev6.feed.view.feedDetailActivity.AdoptFeedDetailActivity
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.collectLatest


class ProfileAdoptFragment :
    BindingFragment<FragmentProfileAdoptBinding>(R.layout.fragment_profile_adopt) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var profileAdoptRc: RecyclerView
    private lateinit var profiledoptAdapter: AdoptPagingAdapter

    override fun initView() {
        super.initView()
        profileAdoptRc = binding.profileAdoptRc
        profiledoptAdapter = AdoptPagingAdapter{
            val adoptIntent = Intent(context, AdoptFeedDetailActivity::class.java)
            adoptIntent.putExtra("adoptPostFeed", it)
            startActivity(adoptIntent)
        }

        profileAdoptRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = profiledoptAdapter
        }


    }

    override fun initViewModel() {
        super.initViewModel()
        feedViewModel.getAdoptList("")
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            feedViewModel.eventAdoptList.collect { event ->2
                when (event) {
                    is FeedViewModel.Event.AdoptUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                Toast.makeText(context, "성공 했어여", Toast.LENGTH_SHORT).show()
                                event.uiState.data.collectLatest {
                                    profiledoptAdapter.submitData(it)
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
                    else -> {}
                }
            }
        }
    }
}