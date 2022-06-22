package com.dev6.post

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.login.viewmodel.PostViewModel
import com.dev6.post.databinding.FragmentLifePostBinding
import gun0912.tedimagepicker.builder.TedImagePicker

class LifePostFragment : BindingFragment<FragmentLifePostBinding>(R.layout.fragment_life_post) {

    private val postViewModel: PostViewModel by activityViewModels()

    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun afterViewCreated() {

        repeatOnStarted {
            postViewModel.postImageFlow.collect { urlList ->
                if (urlList.isNotEmpty()) {
                    Glide.with(this@LifePostFragment).load(urlList[0])
                        .into(binding.vCamera.getImage())
                }
            }
        }

        if (postViewModel.postImageFlow.value.isEmpty()) {
            TedImagePicker.with(requireContext())
                .max(5, "더이상 사진을 넣을수 없습니다")
                .startMultiImage { uriList ->
                    postViewModel.setPostImage(uriList)
                }
        }
        super.afterViewCreated()
    }

    override fun initListener() {
        binding.vCamera.setOnClickListener {
            findNavController().navigate(R.id.action_lifePostFragment_to_gallraryFragment)
        }
        super.initListener()
    }
}