package com.dev6.feed.view.profileDetailFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentProfileIntroduceBinding
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.ProfileViewModel


class ProfileIntroduceFragment :
    BindingFragment<FragmentProfileIntroduceBinding>(R.layout.fragment_profile_introduce) {

    private val profileViewModel : ProfileViewModel by activityViewModels()
     var userId: String = ""

    override fun initView() {
        super.initView()

        userId = arguments?.getString("userId") ?: ""
        //userId 가 "" 라는건 쉘터 클릭으로 들어온게 아니라는것
        when (userId) {
            "" -> when(UserData.userType){
                "NORMAL"->{
                    profileViewModel.getNormalUserProfileDetailData(UserData.userId)
                }
                "SHELTER"->{
                    profileViewModel.getShelterProfileDetailData(UserData.userId)
                }
            }
            else -> {
                profileViewModel.getClickShelterProfileDetailData(userId)
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            Log.v("sdfsdfqvbwv" ,"Sssdfsdfs1")
            //콜렉트가 잘 안되고 있음 이거 해결하면 쉘터클릭시 해당 데이터 가져오는거 끝
            profileViewModel.eventClickProfileDetail.collect{ evnet->
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
                            else->{}
                        }
                    }else->{}
                }
            }
        }
    }
}