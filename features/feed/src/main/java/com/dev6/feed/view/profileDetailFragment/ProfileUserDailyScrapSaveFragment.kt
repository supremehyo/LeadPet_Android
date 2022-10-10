package com.dev6.feed.view.profileDetailFragment

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.data.model.saved.ScrapEventData
import com.dev6.feed.R
import com.dev6.feed.adapter.Saved.SavedDailyAdapter
import com.dev6.feed.adapter.Saved.SavedDonationAdapter
import com.dev6.feed.databinding.FragmentProfileUserScrapSaveBinding
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.SaveViewModel


class ProfileUserDailyScrapSaveFragment : BindingFragment<FragmentProfileUserScrapSaveBinding>
    (R.layout.fragment_profile_user_scrap_save){

    private val feedViewModel : FeedViewModel by activityViewModels()
    private val saveViewModel : SaveViewModel by activityViewModels()
    private lateinit var savedDailyAdapter: SavedDailyAdapter

    override fun onStart() {
        super.onStart()
        Log.v("떼스트","데일리")
        savedDailyAdapter.refresh()
    }

    override fun initView() {
        super.initView()
        Log.v("dfgsdrgg" , "gergergerg")
        savedDailyAdapter()
    }

    override fun initViewModel() {
        super.initViewModel()
        saveViewModel.getDailyListByUserId(UserData.userId)
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()

        repeatOnStarted {
            saveViewModel.eventSavedDailyList.collect{ evnet->
                when(evnet){
                    is SaveViewModel.Event.SavedDailyUiEvent->{
                        when(evnet.uiState){
                            is UiState.Success->{
                                evnet.uiState.data.collect{
                                    savedDailyAdapter.submitData(it)
                                }
                            }
                            is UiState.Error -> {
                                Toast.makeText(context, "실패 했어여", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(context, "로딩 했어여", Toast.LENGTH_SHORT).show()
                            }
                            else->{}
                        }
                    }else->{}
                }
            }
        }
    }

    private fun savedDailyAdapter(){
        savedDailyAdapter = SavedDailyAdapter {
            feedViewModel.setScrapDetailEvent(ScrapEventData("NORMAL", it))
        }
        binding.savedDailyRc.apply {
            adapter = savedDailyAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }
}