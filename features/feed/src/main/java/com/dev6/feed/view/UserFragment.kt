package com.dev6.feed.view

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentUserBinding
import com.dev6.feed.view.profileDetailFragment.ProfileUserDonationFragment
import com.dev6.feed.view.profileDetailFragment.ProfileUserScrapFragment
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.ProfileViewModel
import com.google.android.material.tabs.TabLayout


class UserFragment : BindingFragment<FragmentUserBinding>(R.layout.fragment_user){

    private val feedViewModel: FeedViewModel by activityViewModels()
    private val profileViewModel : ProfileViewModel by activityViewModels()
    lateinit var profileUserScrapFragment: ProfileUserScrapFragment
    lateinit var profileUserDonationFragment: ProfileUserDonationFragment

    lateinit var selected: Fragment

    override fun initView() {
        //  initTabLayout()
        //    profileUserDonationFragment = ProfileUserDonationFragment()
        profileUserScrapFragment = ProfileUserScrapFragment()
        childFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, profileUserScrapFragment).commit()
    }

    override fun initViewModel() {
        //TODO 해당 유저의 uuid 로 관련 피드 데이터 요청
        Log.v("sdfsdfa" , UserData.userId)
        profileViewModel.getNormalUserProfileDetailData(UserData.userId)
    }

    override fun initListener() {
        //TODO 해당 피드를 눌렀을때 post id 를 가지고 있다가 이동
        binding.userFollowTv.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_userProfileUpdateFragment)
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStartedFragment {
            profileViewModel.eventNormalUserProfileDetail.collect{ evnet->
                when(evnet){
                    is ProfileViewModel.Event.NormalUserProfileUiEvent->{
                        when(evnet.uiState){
                            is UiState.Success->{
                                var userProfileData = evnet.uiState.data
                                binding.apply {
                                    Glide.with(binding.root)
                                        .load(Uri.parse(""))
                                        .error(R.drawable.dailay_image1)
                                        .circleCrop()
                                        .into(userProfileIv)
                                    userProfileDesTv.text = userProfileData.intro
                                    userProfileNameTv.text = userProfileData.userName
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

    private fun initTabLayout() {
        binding.apply {
            userProfileTabs.run {
             //   addTab(newTab().setText("기부"))
                addTab(newTab().setText("저장"))
            }

            userProfileTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        /*
                        0 -> {
                            selected = profileUserDonationFragment
                        }

                         */
                        0 -> {
                            selected = profileUserScrapFragment
                        }
                    }
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, selected).commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }
    }
}