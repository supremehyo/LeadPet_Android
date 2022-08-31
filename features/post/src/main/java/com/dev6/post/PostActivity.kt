package com.dev6.post

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.dev6.core.base.BindingActivity
import com.dev6.core.enums.PostType
import com.dev6.post.databinding.ActivityPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : BindingActivity<ActivityPostBinding>(R.layout.activity_post) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postType: PostType = intent.getSerializableExtra("postType") as PostType

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        var navGraph = navController.navInflater.inflate(R.navigation.post_nav_graph)

        when (postType) {
            PostType.DAILY -> {
                navGraph.setStartDestination(R.id.lifePostFragment)
            }
            PostType.ADOPT -> {
                navGraph.setStartDestination(R.id.petAdoptPostFragment)
            }
            PostType.DONATE -> {
                navGraph.setStartDestination(R.id.petDonateFragment)
            }
        }
        navController.setGraph(navGraph, null)
    }
}
