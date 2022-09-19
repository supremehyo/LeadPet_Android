package com.dev6.feed.view

import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.databinding.FragmentUserProfileUpdateBinding
import com.dev6.feed.viewmodel.ProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class UserProfileUpdateFragment : BindingFragment<FragmentUserProfileUpdateBinding>(R.layout.fragment_user_profile_update) {
    private val profileViewModel : ProfileViewModel by activityViewModels()

    override fun initView() {
        super.initView()

    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
        binding.citySelectBt.setOnClickListener {
            val bottomSheet = BottomSheetDialogFragment()
            bottomSheet.show(parentFragmentManager,bottomSheet.tag)
        }

    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStartedFragment {
            profileViewModel.cityChoiceStateFlow.collect{
                binding.citySelectBt.text = it
            }
        }
    }
}