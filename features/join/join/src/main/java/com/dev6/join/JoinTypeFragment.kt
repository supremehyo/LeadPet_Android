package com.dev6.join

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.join.databinding.FragmentJoinTypeBinding

class JoinTypeFragment : BindingFragment<FragmentJoinTypeBinding>(R.layout.fragment_join_type){


    var userType : String ="normal"



    override fun initView() {

        Log.v("email" , arguments?.get("email").toString())//잘넘어옴 확인

        binding.normalButton.setOnClickListener {
            findNavController().navigate(R.id.action_joinTypeFragment_to_normalUserJoinFragment)
        }

        binding.joinTypeBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.shelterButton.setOnClickListener {
            findNavController().navigate(R.id.action_joinTypeFragment_to_shelterUserJoinFragment)
        }
    }
}