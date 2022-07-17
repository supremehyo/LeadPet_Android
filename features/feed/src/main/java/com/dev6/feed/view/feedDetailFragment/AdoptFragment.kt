package com.dev6.feed.view.feedDetailFragment


import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.PostOptionType
import com.dev6.domain.util.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.adapter.AdoptAdapter
import com.dev6.feed.adapter.AdoptPagingAdapter
import com.dev6.feed.adapter.DonationPagingAdapter
import com.dev6.feed.databinding.FragmentAdoptBinding
import com.dev6.feed.option.BottomSheet
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber


class AdoptFragment : BindingFragment<FragmentAdoptBinding>(R.layout.fragment_adopt) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var bottomSheet: BottomSheet

    private lateinit var adoptRc: RecyclerView
    private lateinit var recommendAdoptAdapter: AdoptPagingAdapter


    override fun initView() {

        recommendAdoptAdapter = AdoptPagingAdapter()
        adoptRc = binding.adoptRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recommendAdoptAdapter
        }
    }

    override fun initViewModel() {


    }

    override fun initListener() {
        binding.adoptOption.setOnClickListener {
            makeBottomSheet(R.layout.fragment_bottom_sheet)
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        getAdoptList()
        repeatOnStarted {
            feedViewModel.eventFlow.collect { event ->2
                when (event) {
                    is FeedViewModel.Event.AdoptUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                Toast.makeText(context, "성공 했어여", Toast.LENGTH_SHORT).show()
                                event.uiState.data.collectLatest {
                                    recommendAdoptAdapter.submitData(it)
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

    private fun getAdoptList() {
        feedViewModel.getAdoptList()
    }

    private fun makeBottomSheet(type: Int) {
        bottomSheet = BottomSheet({
            sortFeed(it)
        }, type)
    }

    //아래 메소드에서 소팅 함수 and 리스트 변경 notify
    private fun sortFeed(type: PostOptionType) {
        when (type) {
            PostOptionType.FEED -> {
                Timber.tag("sfsdf").v("피드")
            }
            PostOptionType.THING -> {

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