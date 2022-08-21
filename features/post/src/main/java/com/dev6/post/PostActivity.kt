package com.dev6.post

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
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


        navController.graph = navGraph

        when (postType) {
            PostType.LIFE -> {
                navGraph.setStartDestination(R.layout.fragment_life_post)

            }
            PostType.ADOPT -> {

                navGraph.setStartDestination(R.layout.fragment_pet_adopt_post)

            }
            PostType.DONATE -> {
                navGraph.setStartDestination(R.layout.fragment_pet_donate)

            }
        }
        navController.graph = navGraph
    }
}