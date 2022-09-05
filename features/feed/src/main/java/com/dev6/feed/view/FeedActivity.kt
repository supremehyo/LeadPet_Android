package com.dev6.feed.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dev6.core.base.BindingActivity
import com.dev6.core.enums.FeedViewType
import com.dev6.core.enums.PostType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.R
import com.dev6.feed.databinding.ActivityFeedBinding
import com.dev6.feed.viewmodel.FeedViewModel
import com.dev6.post.PostActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedActivity : BindingActivity<ActivityFeedBinding>(R.layout.activity_feed) {

    private val feedViewModel: FeedViewModel by viewModels()
    var list = listOf("경기도 성남시", "서울특별시")
    var userType = ""

    lateinit var navController: NavController
    var isFabOpen = false

    override fun initView() {
        super.initView()
        initFab()
        initListener()
        initNavController()
    }

    private fun initFab() {
        binding.llFab.translationX = 100f
        binding.llFab.alpha = 0f
        binding.flDim.alpha = 0f
    }

    private fun clickPostFab() {
        isFabOpen = !isFabOpen

        if (isFabOpen) {
            AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(binding.fabPost, View.ROTATION, 45f))
                    .with(ObjectAnimator.ofFloat(binding.llFab, View.TRANSLATION_X, 0f))
                    .with(ObjectAnimator.ofFloat(binding.llFab, View.ALPHA, 1f))
                    .with(ObjectAnimator.ofFloat(binding.flDim, View.ALPHA, 1f))
            }.apply {
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator, isReverse: Boolean) {
                        super.onAnimationStart(animation, isReverse)
                        binding.llFab.visibility = View.VISIBLE
                        binding.flDim.visibility = View.VISIBLE
                    }
                })
                start()
            }
        } else {
            AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(binding.fabPost, View.ROTATION, 0f))
                    .with(ObjectAnimator.ofFloat(binding.llFab, View.TRANSLATION_X, 100f))
                    .with(ObjectAnimator.ofFloat(binding.llFab, View.ALPHA, 0f))
                    .with(ObjectAnimator.ofFloat(binding.flDim, View.ALPHA, 0f))
            }.apply {
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(p0: Animator) {
                        binding.llFab.visibility = View.INVISIBLE
                        binding.flDim.visibility = View.INVISIBLE
                    }
                })
                start()
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        binding.viewmodel = feedViewModel
        feedViewModel.setSpinnerEntry(list)
        repeatOnStarted {
            feedViewModel.spinnerData.collect {
            }
        }

        repeatOnStarted {
            feedViewModel.viewNameData.collect {
                changeHeader(it)
            }
        }
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
    }

    // 바텀 네비게이션 뷰 초기화
    fun initNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.feed_nav_graph)
        if (userType == "user") { // 유저 type이 user면 해당 바텀네비게이션으로 다시 그리기
            navController.setGraph(R.navigation.feed_nav_graph2)
            binding.bottomNavigationView.menu.clear()
            binding.bottomNavigationView.inflateMenu(R.menu.bottome_menu2)
        }
        navHostFragment?.let { binding.bottomNavigationView.setupWithNavController(navController) }
        binding.bottomNavigationView.itemIconTintList = null
    }

    private fun changeHeader(state: FeedViewType) {
        when (state) {
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

    override fun initListener() {
        binding.fabPost.setOnClickListener { clickPostFab() }
        binding.flDim.setOnClickListener { clickPostFab() }
        binding.btnAdopt.setOnClickListener {
            startPostActivity(PostType.ADOPT)
        }
        binding.btnDonate.setOnClickListener {
            startPostActivity(PostType.DONATE)
        }
        binding.btnLife.setOnClickListener {
            startPostActivity(PostType.DAILY)
        }
        super.initListener()
    }

    private fun startPostActivity(postType: PostType) {
        val postIntent = Intent(this, PostActivity::class.java)
        postIntent.putExtra("postType", postType)
        startActivity(postIntent)
    }
}
