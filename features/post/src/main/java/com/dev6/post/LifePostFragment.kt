package com.dev6.post

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.post.databinding.FragmentLifePostBinding
import com.dev6.post.viewmodel.LifePostViewModel
import gun0912.tedimagepicker.builder.TedImagePicker

class LifePostFragment : BindingFragment<FragmentLifePostBinding>(R.layout.fragment_life_post) {

    private val postViewModel: LifePostViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        binding.include.tvTop.text = "일상글"
    }

    override fun initViewModel() {
        super.initViewModel()
        if (postViewModel.postImageFlow.value.isEmpty()) {
            TedImagePicker.with(requireContext()).max(5, "더이상 사진을 넣을수 없습니다")
                .startMultiImage { uriList ->
                    postViewModel.setPostImage(uriList)
                }
        }
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

        repeatOnStarted {
            postViewModel.eventFlow.collect { event ->
                when (event) {
                    is LifePostViewModel.Event.UiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                Toast.makeText(context, "성공 했어여", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Error -> {
                                Toast.makeText(
                                    context, event.uiState.error?.message, Toast.LENGTH_SHORT
                                ).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(context, "로딩 했어여", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

        }
        super.afterViewCreated()
    }

    override fun initListener() {
        binding.vCamera.setOnClickListener {
            val gallraryFragmentDirections =
                LifePostFragmentDirections.actionLifePostFragmentToGallraryFragment(postViewModel.postImageFlow.value.map {
                    it.toString()
                }.toTypedArray())

            findNavController().navigate(gallraryFragmentDirections)
        }

        binding.include.tvRight.setOnClickListener {
            postViewModel.insertLifePost(
                binding.tvLifeTitle.text.toString(),
                binding.tietIntroduce.text.toString(),
            )
        }

        binding.include.tvLeft.setOnClickListener {
            requireActivity().finish()
        }
        super.initListener()
    }
}