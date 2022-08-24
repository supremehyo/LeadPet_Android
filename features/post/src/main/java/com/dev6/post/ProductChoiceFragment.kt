package com.dev6.post

import android.os.Bundle
import android.view.View
import com.dev6.core.base.BindingFragment
import com.dev6.post.databinding.FragmentProductChoiceBinding
import com.xwray.groupie.GroupieAdapter

class ProductChoiceFragment : BindingFragment<FragmentProductChoiceBinding>(R.layout.fragment_product_choice) {

    lateinit var productChoiceAdapter: GroupieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
