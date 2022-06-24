package com.dev6.post

import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.login.viewmodel.DonatePostViewModel
import com.dev6.post.databinding.FragmentPetChoiceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetChoiceFragment : BindingFragment<FragmentPetChoiceBinding>(R.layout.fragment_pet_choice) {

    private val postViewModel: DonatePostViewModel by activityViewModels()
}