package com.dev6.post.viewpager

import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.post.R
import com.dev6.post.databinding.ViewpagerImageBinding

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