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
import com.dev6.feed.adapter.DonationPagingAdapter
import com.dev6.feed.databinding.FragmentProfileDonationBinding
import com.dev6.feed.view.feedDetailActivity.DonationFeedDetailActivity
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.collectLatest


class ProfileDonationFragment :
    BindingFragment<FragmentProfileDonationBinding>(R.layout.fragment_profile_donation) {

    private  val feedViewModel : FeedViewModel by activityViewModels()
    private lateinit var profileDonationRc: RecyclerView
    private lateinit var profileDonationAdapter: DonationPagingAdapter



    override fun initView() {
        super.initView()
        profileDonationRc = binding.profileDonationRc
        profileDonationAdapter = DonationPagingAdapter {
            val donationIntent = Intent(context, DonationFeedDetailActivity::class.java)
            donationIntent.putExtra("donationPostFeed", it)
            startActivity(donationIntent)
        }
        profileDonationRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = profileDonationAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        feedViewModel.getDonationList("app1")
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            feedViewModel.eventDonationList.collect { event ->
                when (event) {
                    is FeedViewModel.Event.DonationUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                Toast.makeText(context, "성공 했어여", Toast.LENGTH_SHORT).show()
                                event.uiState.data.collectLatest {
                                    profileDonationAdapter.submitData(it)
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