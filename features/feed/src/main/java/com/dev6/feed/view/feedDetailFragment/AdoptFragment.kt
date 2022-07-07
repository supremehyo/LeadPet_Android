package com.dev6.feed.view.feedDetailFragment


import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.PostOptionType
import com.dev6.feed.R
import com.dev6.feed.adapter.AdoptAdapter
import com.dev6.feed.databinding.FragmentAdoptBinding
import com.dev6.feed.option.BottomSheet
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber


class AdoptFragment : BindingFragment<FragmentAdoptBinding>(R.layout.fragment_adopt) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var bottomSheet: BottomSheet

    override fun initView() {
        binding.adoptRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AdoptAdapter {

            }
        }
    }

    override fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                feedViewModel.getAdoptList().collectLatest {
                 //   pagingAdapter.submitData(it)
                }
            } catch (e: Exception) {
                Timber.d("에러 $e")
            }
        }


    }

    override fun initListener() {
        binding.adoptOption.setOnClickListener {
            makeBottomSheet(R.layout.fragment_bottom_sheet)
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }
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