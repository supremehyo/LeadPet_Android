package com.dev6.join

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.join.databinding.FragmentEmailJoinBinding
import com.dev6.join.viewmodel.JoinViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EmailJoinFragment : BindingFragment<FragmentEmailJoinBinding>(R.layout.fragment_email_join) {

    lateinit var bottomSheetView: View
    lateinit var bottomSheetDialog: BottomSheetDialog
    var email: String = ""

    private val joinViewModel: JoinViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_laytout, null)
        bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)
    }

    override fun initListener() {
        binding.apply {
            emailJoinButton.setOnClickListener {
                if (binding.editText2.text.toString() == binding.editText3.text.toString()) {
                    bottomSheetDialog.show()
                } else {
                    Toast.makeText(requireContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val termsConfirmButton = bottomSheetView.findViewById<Button>(R.id.termsConfirmButton)
        termsConfirmButton.setOnClickListener {
            bottomSheetDialog.dismiss()
            findNavController().navigate(
                R.id.action_emailJoinFragment_to_joinTypeFragment,
                bundleOf(
                    "email" to binding.editText.text.toString(),
                    "password" to binding.editText2.text.toString()
                )
            )
        }

        val terms1 = bottomSheetView.findViewById<ImageView>(R.id.terms1)
        val terms2 = bottomSheetView.findViewById<ImageView>(R.id.terms2)
        val terms3 = bottomSheetView.findViewById<ImageView>(R.id.terms3)
        val termsBottomSheetClose =
            bottomSheetView.findViewById<ImageView>(R.id.termsBottomSheetClose)

        terms1.setOnClickListener {
            findNavController().navigate(R.id.emailJoinFragment_to_termsFragment)
        }
        terms2.setOnClickListener {
            findNavController().navigate(R.id.emailJoinFragment_to_termsFragment)
        }
        terms3.setOnClickListener {
            findNavController().navigate(R.id.emailJoinFragment_to_termsFragment)
        }
        termsBottomSheetClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

    }
}