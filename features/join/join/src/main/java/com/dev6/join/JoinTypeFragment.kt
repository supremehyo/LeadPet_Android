package com.dev6.join

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dev6.core.base.BindingFragment
import com.dev6.join.databinding.FragmentJoinTypeBinding
import com.dev6.join.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

class JoinTypeFragment : BindingFragment<FragmentJoinTypeBinding>(R.layout.fragment_join_type){

    var userType : String ="normal"

    override fun initView() {


        binding.normalButton.setOnClickListener {
            userType = "NORMAL"
            findNavController().navigate(R.id.action_joinTypeFragment_to_normalUserJoinFragment ,
                Bundle().apply {putString("userType", userType)})
        }

        binding.joinTypeBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.shelterButton.setOnClickListener {
            userType = "shelter"
            findNavController().navigate(R.id.action_joinTypeFragment_to_shelterUserJoinFragment,
                Bundle().apply {putString("userType", userType)})
        }
    }
}