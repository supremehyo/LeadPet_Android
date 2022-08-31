package com.dev6.post

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dev6.core.base.BindingFragment
import com.dev6.post.databinding.FragmentGallraryBinding
import com.dev6.post.viewpager.ViewPagerImage

class GallraryFragment : BindingFragment<FragmentGallraryBinding>(R.layout.fragment_gallrary) {

    val args: GallraryFragmentArgs by navArgs()

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
        binding.ibClose.setOnClickListener { findNavController().popBackStack() }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = args.image.size
        override fun createFragment(position: Int): Fragment = ViewPagerImage(position, args.image)
    }
}