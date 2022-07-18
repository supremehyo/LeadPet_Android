package com.dev6.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.login.viewmodel.DonatePostViewModel
import com.dev6.post.databinding.FragmentSpeciesChoiceBinding
import com.dev6.post.viewmodel.AdoptPostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpeciesChoiceFragment : BindingFragment<FragmentSpeciesChoiceBinding>(R.layout.fragment_species_choice) {

    private val postViewModel: AdoptPostViewModel by activityViewModels()
    lateinit var  


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun initView() {
        super.initView()
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

    override fun onDestroyView() {
        super.onDestroyView()
    }
}