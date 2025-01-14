package com.dev6.feed.view.profileDetailFragment
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dev6.core.UserData
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentProfileUserScrapBinding
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.feed.viewmodel.SaveViewModel
import com.google.android.material.tabs.TabLayout


class ProfileUserScrapFragment : BindingFragment<FragmentProfileUserScrapBinding>
    (R.layout.fragment_profile_user_scrap){

    private val saveViewModel : SaveViewModel by activityViewModels()
    private val feedViewModel : FeedViewModel by activityViewModels()

    lateinit var profileUserDailyScrapSaveFragment: ProfileUserDailyScrapSaveFragment
    val profileUserDonationScrapSaveFragment: ProfileUserDonationScrapSaveFragment by lazy { ProfileUserDonationScrapSaveFragment() }
    val profileUserAdoptionScrapSaveFragment: ProfileUserAdoptionScrapSaveFragment by lazy { ProfileUserAdoptionScrapSaveFragment() }
    lateinit var selected: Fragment

    override fun initView() {
        super.initView()
        profileUserDailyScrapSaveFragment = ProfileUserDailyScrapSaveFragment()
        childFragmentManager.beginTransaction().replace(R.id.profileSavedFc, profileUserDailyScrapSaveFragment).commit()
        initTabLayout()
        Log.v("떼스트","유저프라그먼트스크랩")
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    private fun initTabLayout(){
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        selected = profileUserDailyScrapSaveFragment
                    }
                    1 -> {
                        selected = profileUserDonationScrapSaveFragment
                    }
                    2 -> {
                        selected = profileUserAdoptionScrapSaveFragment
                    }
                }
                childFragmentManager.beginTransaction()
                    .replace(R.id.profileSavedFc, selected).commit()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}