package com.dev6.core.base

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<B : ViewDataBinding>(private val layoutId: Int) :
    DialogFragment() {
    lateinit var binding: B
    lateinit var activity: Activity
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = requireActivity()
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        setBackgroundDialog()
        return binding.root
    }
    
    open fun setCancel(setting: Boolean) {
        isCancelable = setting
    }
    
    
    open fun setBackgroundDialog() {}
}