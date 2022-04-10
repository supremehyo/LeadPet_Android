package com.dev6.feed.view

import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dev6.core.base.BindingActivity
import com.dev6.feed.R
import com.dev6.feed.databinding.ActivityFeedBinding
import com.dev6.feed.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedActivity : BindingActivity<ActivityFeedBinding>(R.layout.activity_feed) {

    private  val feedViewModel : FeedViewModel by viewModels()



    override fun initView() {
        super.initView()
        initNavController()
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun afterOnCreate() {
        super.afterOnCreate()


    }

    //바텀 네비게이션 뷰 초기화
    fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navHostFragment?.let { binding.bottomNavigationView.setupWithNavController(it.findNavController()) }
        binding.bottomNavigationView.itemIconTintList = null
    }
}