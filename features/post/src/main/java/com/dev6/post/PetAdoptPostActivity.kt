package com.dev6.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingActivity
import com.dev6.post.databinding.ActivityLifePostBinding
import com.dev6.post.databinding.ActivityPetAdoptPostBinding
import com.dev6.post.item.ItemChoiceAnimal
import com.dev6.post.item.ItemSerchAnimal
import com.xwray.groupie.GroupieAdapter

class PetAdoptPostActivity :
    BindingActivity<ActivityPetAdoptPostBinding>(R.layout.activity_pet_adopt_post) {

    override fun beforeSetContentView() {
        super.beforeSetContentView()
    }

    override fun initView() {
        super.initView()
        val choiceAdapter = GroupieAdapter()

        binding.rvAnimalChoice.adapter = choiceAdapter
//        binding.rvAnimalChoice.layoutManager.orientation = LinearLayoutManager.HORIZONTAL


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

    override fun afterOnCreate() {
        super.afterOnCreate()
    }
}