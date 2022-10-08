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
import com.dev6.feed.R
import com.dev6.feed.adapter.Saved.SavedDonationAdapter
import com.dev6.feed.databinding.FragmentProfileUserDonationScrapSaveBinding
import com.dev6.feed.viewmodel.SaveViewModel
import kotlinx.coroutines.flow.collect

class ProfileUserDonationScrapSaveFragment : BindingFragment<FragmentProfileUserDonationScrapSaveBinding>
    (R.layout.fragment_profile_user_donation_scrap_save){
    private val saveViewModel : SaveViewModel by activityViewModels()
    private lateinit var savedDonationAdapter : SavedDonationAdapter

    override fun initView() {
        super.initView()
        savedDonationAdapter()
    }

    override fun initViewModel() {
        super.initViewModel()
        saveViewModel.getDonationListByUserId(UserData.userId)
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()

        repeatOnStarted {
            saveViewModel.eventDonationList.collect{ evnet->
                when(evnet){
                    is SaveViewModel.Event.SavedDonationUiEvent->{
                        when(evnet.uiState){
                            is UiState.Success->{
                                evnet.uiState.data.collect{
                                    savedDonationAdapter.submitData(it)
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

    private fun savedDonationAdapter(){
        savedDonationAdapter = SavedDonationAdapter {}
        binding.donationRc.apply {
            adapter = savedDonationAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }
}