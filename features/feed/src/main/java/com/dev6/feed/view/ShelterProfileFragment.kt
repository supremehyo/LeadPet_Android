package com.dev6.feed.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.domain.model.ShelterEntitiyRepo
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentProfileBinding
import com.dev6.feed.view.profileDetailFragment.ProfileAdoptFragment
import com.dev6.feed.view.profileDetailFragment.ProfileDailyFragment
import com.dev6.feed.view.profileDetailFragment.ProfileDonationFragment
import com.dev6.feed.view.profileDetailFragment.ProfileIntroduceFragment
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.ProfileViewModel
import com.google.android.material.tabs.TabLayout


class ShelterProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val feedViewModel: FeedViewModel by activityViewModels()
    private val profileViewModel : ProfileViewModel by activityViewModels()
    lateinit var profileIntroduceFragment: ProfileIntroduceFragment
    lateinit var profileDailyFragment: ProfileDailyFragment
    lateinit var profileDonationFragment: ProfileDonationFragment
    lateinit var profileAdoptFragment: ProfileAdoptFragment
    lateinit var selected: Fragment
    var shelterData : ShelterEntitiyRepo? = null


    override fun initView() {
        super.initView()
        initTabLayout()

        shelterData = arguments?.get("shelterData") as ShelterEntitiyRepo?
        profileIntroduceFragment = ProfileIntroduceFragment()
        profileDailyFragment = ProfileDailyFragment()
        profileDonationFragment = ProfileDonationFragment()
        profileAdoptFragment = ProfileAdoptFragment()

        shelterData?.let {
            val bundle = Bundle()
            bundle.putString("userId", shelterData?.userId)
            profileIntroduceFragment.setArguments(bundle)
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, profileIntroduceFragment).commit()


        if(shelterData?.userId == UserData.userId){
            binding.profileUpdateTv.visibility = View.VISIBLE
        }else{//자신의 계정이 아니면 프로필 수정 버튼 감춤
            binding.profileUpdateTv.visibility = View.INVISIBLE
        }


    }

    override fun initViewModel() {
        super.initViewModel()
        feedViewModel.setCurrentView(FeedViewType.PROFILE)
        profileViewModel.getShelterProfileDetailData(shelterData?.userId ?: "")
    }

    override fun initListener() {
        super.initListener()
        binding.profileUpdateTv.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_shelterProfileUpdateFragment)
        }
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
                                    Glide.with(binding.root)
                                        .load(R.mipmap.img_1)
                                        .circleCrop()
                                        .into(shelterProfileImageIv)
                                    shelterProfileDesTv.text = userProfileData.shelterIntro
                                    shelterProfileNameTv.text = userProfileData.shelterName
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
            profileTabs.run {
                addTab(newTab().setText("소개"))
                addTab(newTab().setText("일상"))
                addTab(newTab().setText("기부"))
                addTab(newTab().setText("입양"))
            }

            profileTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {

                    // 눌렀을때 색 변경이랑 네이게이션 이동 코드
                    when (tab?.position) {
                        0 -> {
                            selected = profileIntroduceFragment

                            //feedViewModel.setCurrentView(FeedViewType.TOTAL)
                        }
                        1 -> {
                            selected = profileDailyFragment
                            //feedViewModel.setCurrentView(FeedViewType.DAILY)
                        }
                        2 -> {
                            selected = profileDonationFragment
                            //feedViewModel.setCurrentView(FeedViewType.DONATION)
                        }
                        3 -> {
                            selected = profileAdoptFragment

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