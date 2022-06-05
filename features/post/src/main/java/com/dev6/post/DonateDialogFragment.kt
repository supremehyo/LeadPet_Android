package com.dev6.post

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.dev6.core.base.BaseDialogFragment
import com.dev6.post.databinding.FragmentDonateDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DonateDialogFragment : BaseDialogFragment<FragmentDonateDialogBinding>(R.layout.fragment_donate_dialog) {
    
    override fun setBackgroundDialog() {
        super.setBackgroundDialog()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
    }
}