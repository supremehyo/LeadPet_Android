package com.dev6.post.viewpager

import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.login.viewmodel.DonatePostViewModel
import com.dev6.post.R
import com.dev6.post.databinding.ViewpagerImageBinding

class ViewPagerImage(private val position: Int) :
    BindingFragment<ViewpagerImageBinding>(R.layout.viewpager_image) {

    private val postViewModel: DonatePostViewModel by activityViewModels()


    override fun initView() {
        super.initView()
        Glide.with(this).load(postViewModel.postImageFlow.value[position]).into(binding.imageView3)
    }
}