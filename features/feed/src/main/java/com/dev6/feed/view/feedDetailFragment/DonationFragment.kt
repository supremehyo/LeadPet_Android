package com.dev6.feed.view.feedDetailFragment

import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.PostOptionType
import com.dev6.data.entity.donation.DonationPaginationResponse
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.util.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.adapter.DonationPagingAdapter
import com.dev6.feed.adapter.PagingAdapter
import com.dev6.feed.adapter.RecommendDonationAdapter
import com.dev6.feed.databinding.FragmentDonationBinding
import com.dev6.feed.option.BottomSheet
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import timber.log.Timber


class DonationFragment : BindingFragment<FragmentDonationBinding>(R.layout.fragment_donation) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var bottomSheet: BottomSheet
    private lateinit var donationRc: RecyclerView

    private lateinit var recommendDonationAdapter: DonationPagingAdapter

    override fun initView() {
        donationRc = binding.donationRc
        recommendDonationAdapter = DonationPagingAdapter()

        donationRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recommendDonationAdapter
        }
    }

    override fun initListener() {
        binding.apply {
            donationOption1.setOnClickListener {
                makeBottomSheet(R.layout.fragment_bottom_sheet)
                bottomSheet.show(childFragmentManager, bottomSheet.tag)
            }

            donationOption2.setOnClickListener {
                makeBottomSheet(R.layout.fragment_category_sheet)
                bottomSheet.show(childFragmentManager, bottomSheet.tag)
            }
        }
    }
    private fun getDonationList() {
        feedViewModel.getDonationList()
    }
    private fun makeBottomSheet(type: Int) {
        bottomSheet = BottomSheet({
            sortFeed(it)
        }, type)
    }
    override fun afterViewCreated() {
        super.afterViewCreated()
        getDonationList()

        repeatOnStarted {
            feedViewModel.donationPagingFlow.collect { it ->

            }
        }

        repeatOnStarted {
            feedViewModel.eventFlow.collect { event ->2
                when (event) {
                    is FeedViewModel.Event.DonationUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                Toast.makeText(context, "성공 했어여", Toast.LENGTH_SHORT).show()
                                event.uiState.data.collectLatest {
                                    recommendDonationAdapter.submitData(it)
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


    //아래 메소드에서 소팅 함수 and 리스트 변경 notify
    private fun sortFeed(type: PostOptionType) {
        when (type) {
            PostOptionType.FEED -> {

            }
            PostOptionType.THING -> {
                Timber.tag("sfsdf").v("피드")
            }
            PostOptionType.DATE -> {

            }
            PostOptionType.TOTAL -> {

            }
            PostOptionType.RECENT -> {

            }
        }
    }
}