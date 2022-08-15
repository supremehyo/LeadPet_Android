package com.dev6.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev6.core.base.BindingFragment
import com.dev6.post.databinding.FragmentProductChoiceBinding
import com.dev6.post.databinding.FragmentSpeciesChoiceBinding
import com.xwray.groupie.GroupieAdapter

class ProductChoiceFragment : BindingFragment<FragmentProductChoiceBinding>(R.layout.fragment_product_choice) {

    lateinit var breedAdapter: GroupieAdapter

}