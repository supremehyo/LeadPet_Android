package com.dev6.feed.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingActivity
import com.dev6.feed.R
import com.dev6.feed.databinding.ActivityAdoptFeedDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileDetailActivity: BindingActivity<ActivityAdoptFeedDetailBinding>(R.layout.activity_profile_detail)  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}