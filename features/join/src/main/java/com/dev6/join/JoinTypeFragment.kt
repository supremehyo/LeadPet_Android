package com.dev6.join

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dev6.core.base.BindingFragment
import com.dev6.core.databinding.InclueTopBinding
import com.dev6.join.databinding.FragmentJoinTypeBinding
import com.dev6.join.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

class JoinTypeFragment : BindingFragment<FragmentJoinTypeBinding>(R.layout.fragment_join_type){

    var userType : String ="NORMAL"
    private lateinit var exampleBinding: InclueTopBinding
    override fun initView() {


        binding.normalButton.setOnClickListener {
            userType = "NORMAL"
            findNavController().navigate(R.id.action_joinTypeFragment_to_normalUserJoinFragment ,
                Bundle().apply {putString("userType", userType)})
        }

        exampleBinding = InclueTopBinding.inflate(layoutInflater)
        exampleBinding.tvLeft.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.shelterButton.setOnClickListener {
            userType = "SHELTER"
            findNavController().navigate(R.id.action_joinTypeFragment_to_shelterUserJoinFragment,
                Bundle().apply {putString("userType", userType)})
        }
    }
}