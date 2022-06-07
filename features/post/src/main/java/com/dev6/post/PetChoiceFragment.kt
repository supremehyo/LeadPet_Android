package com.dev6.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.login.viewmodel.PostViewModel
import com.dev6.post.databinding.FragmentPetChoiceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetChoiceFragment : BindingFragment<FragmentPetChoiceBinding>(R.layout.fragment_pet_choice) {

    private val postViewModel: PostViewModel by activityViewModels()
}