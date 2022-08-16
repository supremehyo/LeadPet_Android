package com.dev6.feed.view

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dev6.core.base.BindingActivity
import com.dev6.core.enums.FeedViewType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.databinding.ActivityFeedBinding
import com.dev6.feed.viewmodel.FeedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedActivity : BindingActivity<ActivityFeedBinding>(R.layout.activity_feed) {

    private  val feedViewModel : FeedViewModel by viewModels()
    var list  = listOf("경기도 성남시" , "서울특별시")
    var userType = "user"
    lateinit var navController: NavController
    override fun initView() {
        super.initView()

        initNavController()
    }

    override fun initViewModel() {
        super.initViewModel()

        binding.viewmodel = feedViewModel
        feedViewModel.setSpinnerEntry(list)
        repeatOnStarted {
            feedViewModel.spinnerData.collect{

            }
        }

        repeatOnStarted {
            feedViewModel.viewNameData.collect{
                changeHeader(it)
            }
        }
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
    }

    //바텀 네비게이션 뷰 초기화
    fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.feed_nav_graph)
        if(userType == "user"){ // 유저 type이 user면 해당 바텀네비게이션으로 다시 그리기
            navController.setGraph(R.navigation.feed_nav_graph2)
            binding.bottomNavigationView.menu.clear()
            binding.bottomNavigationView.inflateMenu(R.menu.bottome_menu2)
        }
        navHostFragment?.let { binding.bottomNavigationView.setupWithNavController(navController) }
        binding.bottomNavigationView.itemIconTintList = null

    }

    private fun changeHeader(state : FeedViewType){
        when(state){
            FeedViewType.FEED -> {
                binding.logoImage.visibility = View.GONE
                binding.locationSpinner.visibility = View.VISIBLE
            }
            FeedViewType.PROFILE -> {
                binding.logoImage.visibility = View.GONE
                binding.locationSpinner.visibility = View.VISIBLE
            }
            FeedViewType.HOME -> {
                binding.logoImage.visibility = View.VISIBLE
                binding.locationSpinner.visibility = View.GONE
            }
            else -> {}
        }
    }
}