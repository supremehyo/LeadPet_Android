package com.dev6.join

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingActivity
import com.dev6.core.base.BindingFragment
import com.dev6.join.databinding.ActivityJoinBinding
import com.dev6.join.databinding.FragmentEmailJoinBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class EmailJoinFragment : BindingFragment<FragmentEmailJoinBinding>(R.layout.fragment_email_join) {

    override fun initView() {
        super.initView()

        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_laytout, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)

        binding.apply {
                emailJoinButton.setOnClickListener {
                    bottomSheetDialog.show()
                //findNavController().navigate(R.id.emailJoinFragment_to_termsFragment)
            }
        }
    }

    override fun initListener() {

    }

}