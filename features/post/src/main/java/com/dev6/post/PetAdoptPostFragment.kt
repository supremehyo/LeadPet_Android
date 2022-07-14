package com.dev6.post

import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.login.viewmodel.DonatePostViewModel
import com.dev6.post.bottom.GenderBottomSeatFragment
import com.dev6.post.databinding.FragmentPetAdoptPostBinding
import com.dev6.post.item.ItemChoiceAnimal
import com.dev6.post.item.ItemSerchAnimal
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetAdoptPostFragment : BindingFragment<FragmentPetAdoptPostBinding>(R.layout.fragment_pet_adopt_post) {

    private val postViewModel: DonatePostViewModel by activityViewModels()
    lateinit var genderBottomSeatFragment : GenderBottomSeatFragment
    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    override fun initView() {
        super.initView()
        val choiceAdapter = GroupieAdapter()
        genderBottomSeatFragment = GenderBottomSeatFragment()

        binding.rvAnimalChoice.adapter = choiceAdapter

        //todo 리소트 익스텐션 하나 만들기
        binding.include.tvTop.text = resources.getString(R.string.title_adaption_insert)

        //todo 이것도 깔끔하게 정리하기
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
        binding.btnChoiceGender.setOnClickListener {
            genderBottomSeatFragment.show(this.parentFragmentManager , "")
        }
        super.initListener()
    }

}