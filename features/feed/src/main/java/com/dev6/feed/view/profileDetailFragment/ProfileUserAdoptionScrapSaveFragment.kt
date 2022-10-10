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
import com.dev6.domain.model.save.SavedAdoption
import com.dev6.feed.R
import com.dev6.feed.adapter.Saved.SavedAdoptAdapter
import com.dev6.feed.databinding.FragmentProfileUserAdoptionScrapSaveBinding
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.SaveViewModel



class ProfileUserAdoptionScrapSaveFragment : BindingFragment<FragmentProfileUserAdoptionScrapSaveBinding>
    (R.layout.fragment_profile_user_adoption_scrap_save){
    private val saveViewModel : SaveViewModel by activityViewModels()
    private val feedViewModel : FeedViewModel by activityViewModels()
    private lateinit var savedAdoptionAdapter: SavedAdoptAdapter


    override fun onStart() {
        super.onStart()
        Log.v("떼스트","어돕트")
        savedAdoptionAdapter.refresh()
    }

    override fun initView() {
        super.initView()
        savedAdoptionAdapter()
    }

    override fun initViewModel() {
        super.initViewModel()
        saveViewModel.getAdoptionListByUserId(UserData.userId)
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()

        repeatOnStarted {
            saveViewModel.eventAdoptionList.collect{ evnet->
                when(evnet){
                    is SaveViewModel.Event.SavedAdoptUiEvent->{
                        when(evnet.uiState){
                            is UiState.Success->{
                                evnet.uiState.data.collect{
                                    savedAdoptionAdapter.submitData(it)
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

    private fun savedAdoptionAdapter(){
        savedAdoptionAdapter = SavedAdoptAdapter {
            feedViewModel.setScrapDetailEvent(ScrapEventData("ADOPT", it))
        }
        binding.savedAdoptionRc.apply {
            adapter = savedAdoptionAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }
}