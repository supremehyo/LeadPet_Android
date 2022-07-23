package com.dev6.feed.view.profileDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentProfileIntroduceBinding
import com.dev6.feed.viewmodel.FeedViewModel


class ProfileIntroduceFragment :
    BindingFragment<FragmentProfileIntroduceBinding>(R.layout.fragment_profile_introduce) {

    private  val feedViewModel : FeedViewModel by activityViewModels()

    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }
}