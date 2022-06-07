package com.dev6.post

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.login.viewmodel.PostViewModel
import com.dev6.post.databinding.FragmentPetAdoptPostBinding
import com.dev6.post.item.ItemChoiceAnimal
import com.dev6.post.item.ItemSerchAnimal
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedImagePicker

@AndroidEntryPoint
class PetAdoptPostFragment : BindingFragment<FragmentPetAdoptPostBinding>(R.layout.fragment_pet_adopt_post) {

    private val postViewModel: PostViewModel by activityViewModels()

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    override fun initView() {
        super.initView()
        val choiceAdapter = GroupieAdapter()

        binding.rvAnimalChoice.adapter = choiceAdapter

        choiceAdapter.add(ItemChoiceAnimal("믹스견"))
        choiceAdapter.add(ItemChoiceAnimal("치와와"))
        choiceAdapter.add(ItemChoiceAnimal("골든 리트리버"))
        choiceAdapter.add(ItemChoiceAnimal("말티즈"))
        choiceAdapter.add(ItemSerchAnimal())
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {

        super.initListener()
    }

}