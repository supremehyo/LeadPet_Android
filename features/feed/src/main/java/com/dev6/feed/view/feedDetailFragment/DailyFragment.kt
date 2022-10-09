package com.dev6.feed.view.feedDetailFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.enums.FeedViewType
import com.dev6.core.enums.UserType
import com.dev6.feed.R
import com.dev6.feed.adapter.DailyPagingAdapter
import com.dev6.feed.adapter.DailyshelterRecyclerAdapter
import com.dev6.feed.databinding.FragmentDailyBinding
import com.dev6.feed.viewmodel.FeedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DailyFragment : BindingFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var dailyRc: RecyclerView
    private lateinit var dailyNearShelterRc: RecyclerView
    private lateinit var pagingAdapter: DailyPagingAdapter
    private lateinit var shelterAdapter: DailyshelterRecyclerAdapter
    lateinit var navController: NavController
    var userId = ""

    override fun initView() {
        super.initView()
        dailyRc = binding.dailyRc
        dailyNearShelterRc = binding.dailyNearShelterRc

        pagingAdapter = DailyPagingAdapter {
            if (UserData.userType == UserType.NORMAL) {
                feedViewModel.setCurrentView(FeedViewType.FEEDDETAIL)
                findNavController().navigate(
                    R.id.action_feedFragment_to_fragmentFeedDaily,
                    Bundle().apply { putSerializable("dailyPost", it) }
                )
            } else {
                feedViewModel.setCurrentView(FeedViewType.FEEDDETAIL)
                findNavController().navigate(
                    R.id.action_feedFragment_to_fragmentFeedDaily2,
                    Bundle().apply { putSerializable("dailyPost", it) }
                )
            }
        }

        shelterAdapter = DailyshelterRecyclerAdapter {
            if (UserData.userType == UserType.NORMAL) {
                feedViewModel.setCurrentView(FeedViewType.SHELTERPROFILECLICK)
                findNavController().navigate(
                    R.id.action_feedFragment_to_profileFragment2,
                    Bundle().apply { putSerializable("shelterData", it) }
                )
            } else {
                feedViewModel.setCurrentView(FeedViewType.SHELTERPROFILECLICK)
                findNavController().navigate(
                    R.id.action_feedFragment_to_profileFragment,
                    Bundle().apply { putSerializable("shelterData", it) }
                )
            }
        }

        dailyNearShelterRc.apply {
            adapter = shelterAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        dailyRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pagingAdapter
        }
    }

    override fun initViewModel() {
        getDailyList()

    }
    private fun getDailyList() {
        feedViewModel.getFeedList("", "")
    }
    private fun getShelterList(citiy : String) {
        feedViewModel.getNearShelterList(citiy?:"서울", "")
    }
    override fun onStart() {
        pagingAdapter.refresh()
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                getShelterList(feedViewModel.city)
            }
          //  shelterAdapter.refresh()
        }
        Timber.tag("테스트").d("Start")
        super.onStart()
    }
    override fun onResume() {
        Timber.tag("테스트").d("onResume")
        super.onResume()
    }
    override fun onStop() {
        Timber.tag("테스트").d("onStop")
        super.onStop()
    }
    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStartedFragment {
            feedViewModel.spinnerData.collect{
                Log.v("adfavw", it)
                withContext(Dispatchers.IO){
                    getShelterList(it)
                }
                shelterAdapter.refresh()
            }
        }

        repeatOnStartedFragment {
            feedViewModel.eventDailyList.collect { event ->
                when (event) {
                    is FeedViewModel.Event.DailyUiEvent -> {
                        pagingAdapter.submitData(event.pagingData)
                    }
                    else -> {
                    }
                }
            }
        }
        repeatOnStartedFragment {
            feedViewModel.eventShelterList.collect { event ->
                Log.v("Afsdfas13" , "콜렉트")
                shelterAdapter.submitData(event)
                /*
                when (event) {
                    is FeedViewModel.Event.ShelterUiEvent -> {
                        Log.v("Afsdfas12" , "콜렉트")
                        shelterAdapter.submitData(event.uiState)
                    }else->{

                    }
                }

                 */
            }
        }
    }
}
