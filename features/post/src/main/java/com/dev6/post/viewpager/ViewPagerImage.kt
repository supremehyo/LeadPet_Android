package com.dev6.post.viewpager

import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.login.viewmodel.DonatePostViewModel
import com.dev6.post.R
import com.dev6.post.databinding.ViewpagerImageBinding
import com.dev6.post.viewmodel.LifePostViewModel

class ViewPagerImage(private val position: Int, private val image: Array<String>) :
    BindingFragment<ViewpagerImageBinding>(R.layout.viewpager_image) {

//    private val postViewModel: LifePostViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        Glide.with(this).load(image[position].toUri())
            .fitCenter()
            .into(binding.imageView3)
    }
}