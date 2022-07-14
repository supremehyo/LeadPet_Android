package com.dev6.post

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dev6.core.base.BindingFragment
import com.dev6.login.viewmodel.DonatePostViewModel
import com.dev6.post.databinding.FragmentGallraryBinding
import com.dev6.post.viewpager.ViewPagerImage


class GallraryFragment : BindingFragment<FragmentGallraryBinding>(R.layout.fragment_gallrary) {

    private val postViewModel: DonatePostViewModel by activityViewModels()

    private val pagerAdapter by lazy { ScreenSlidePagerAdapter(this) }


    override fun initView() {
        super.initView()
        with(binding) {
            viewPager2.adapter = pagerAdapter
            dotsIndicator.attachTo(binding.viewPager2)
        }

    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = postViewModel.postImageFlow.value.size
        override fun createFragment(position: Int): Fragment = ViewPagerImage(position)
    }
}