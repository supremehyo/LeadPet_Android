package com.dev6.feed.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.SavedAdoptionData
import com.dev6.domain.model.save.SavedDailyData
import com.dev6.domain.model.save.SavedDonationData
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentUserBinding
import com.dev6.feed.view.feedDetailActivity.AdoptFeedDetailActivity
import com.dev6.feed.view.feedDetailActivity.DonationFeedDetailActivity
import com.dev6.feed.view.profileDetailFragment.ProfileUserScrapFragment
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.ProfileViewModel
import com.google.android.material.tabs.TabLayout


class UserFragment : BindingFragment<FragmentUserBinding>(R.layout.fragment_user){

    private val feedViewModel: FeedViewModel by activityViewModels()
    private val profileViewModel : ProfileViewModel by activityViewModels()
    lateinit var profileUserScrapFragment: ProfileUserScrapFragment

    lateinit var selected: Fragment

    override fun onStart() {
        super.onStart()
        Log.v("떼스트","유저프라그먼트")
   }

    override fun initView() {
        feedViewModel.setCurrentView(FeedViewType.PROFILE)
        profileUserScrapFragment = ProfileUserScrapFragment()
        binding.apply {
            userProfileNameTv.text = UserData.shelterName
            userProfileDesTv.text = UserData.intro
        }
        childFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, profileUserScrapFragment).commit()
    }

    override fun initViewModel() {
        profileViewModel.getNormalUserProfileDetailData(UserData.userId)
    }

    override fun initListener() {
        binding.userFollowTv.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_userProfileUpdateFragment)
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()

        repeatOnStartedFragment {
            feedViewModel.scrapDetailEvent.collect{
                when(it.scrapType){
                    "NORMAL"->{
                        var temp = it.data as SavedDailyData
                        var dailyPost = DailyPost(temp.contents,temp.images,temp.normalPostId,
                            temp.title,temp.userName,temp.profileImage,temp.userId
                        ,0, temp.createdDate,0,false , emptyList())

                        feedViewModel.setCurrentView(FeedViewType.FEEDDETAIL)
                        findNavController().navigate(R.id.action_userFragment_to_fragmentFeedDaily ,
                            Bundle().apply { putSerializable("dailyPost", dailyPost)})
                    }
                    "DONATION"->{
                        var temp = it.data as SavedDonationData
                        var donationPost = DonationPost(
                            temp.contents,
                            temp.donationMethod,
                            temp.donationPostId,
                            temp.endDate,
                            temp.images,
                            temp.startDate,
                            temp.userName,
                            temp.profileImage,
                            temp.title,
                            temp.userId
                        )
                        val donationIntent = Intent(context, DonationFeedDetailActivity::class.java)
                        donationIntent.putExtra("donationPostFeed", donationPost)
                        startActivity(donationIntent)
                    }
                    "ADOPT"->{
                        var temp = it.data as SavedAdoptionData
                        var adoptPostFeed = AdoptPostFeed(
                            temp.adoptionPostId,
                            temp.age,
                            temp.animalType,
                            temp.contents ?: "",
                            temp.endDate ,
                            temp.euthanasiaDate ?: "",
                            temp.gender,
                            temp.images,
                            temp.neutering,
                            temp.species,
                            temp.startDate ?: emptyList(),
                            temp.userName,
                            temp.profileImage,
                            temp.title ?: "",
                            temp.userId ?: "",
                            temp.disease,
                            emptyList()
                           // temp.imageByteArrayList
                        )
                        val adoptIntent = Intent(context, AdoptFeedDetailActivity::class.java)
                        adoptIntent.putExtra("adoptPostFeed", adoptPostFeed)
                        startActivity(adoptIntent)
                    }
                }
            }
        }


        repeatOnStartedFragment {
            profileViewModel.eventNormalUserProfileDetail.collect{ evnet->
                when(evnet){
                    is ProfileViewModel.Event.NormalUserProfileUiEvent->{
                        when(evnet.uiState){
                            is UiState.Success->{
                                var userProfileData = evnet.uiState.data
                                binding.apply {
                                    Glide.with(binding.root)
                                        .load(userProfileData.profileImage)
                                        .error(R.mipmap.img_1)
                                        .circleCrop()
                                        .into(userProfileIv)
                                    userProfileDesTv.text = userProfileData.intro
                                    userProfileNameTv.text = userProfileData.userName
                                    UserData.userName = userProfileData.userName
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
                        0 -> {
                            selected = profileUserScrapFragment
                        }
                    }
                    childFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, selected).commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }
    }
}