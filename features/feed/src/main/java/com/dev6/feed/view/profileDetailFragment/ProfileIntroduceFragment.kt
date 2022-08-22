package com.dev6.feed.view.profileDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentProfileIntroduceBinding
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.ProfileViewModel


class ProfileIntroduceFragment :
    BindingFragment<FragmentProfileIntroduceBinding>(R.layout.fragment_profile_introduce) {

    private val profileViewModel : ProfileViewModel by activityViewModels()


    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStartedFragment {
            profileViewModel.eventProfileDetail.collect{ evnet->
                when(evnet){
                    is ProfileViewModel.Event.ProfileUiEvent->{
                        when(evnet.uiState){
                            is UiState.Success->{
                                var userProfileData = evnet.uiState.data
                                binding.apply {
                                    shelterProfileLocationTv.text = userProfileData.shelterAddress
                                    shelterProfileNumberTv.text = userProfileData.shelterPhoneNumber
                                    shelterProfileAccountTv.text = userProfileData.shelterAccount
                                    shelterProfileHomePageTv.text = userProfileData.shelterHomepage
                                }
                            }
                            is UiState.Error -> {
                                Toast.makeText(context, "실패 했어여", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(context, "로딩 했어여", Toast.LENGTH_SHORT).show()
                            }
                            else->{

                            }
                        }
                    }else->{

                }
                }
            }
        }
    }
}