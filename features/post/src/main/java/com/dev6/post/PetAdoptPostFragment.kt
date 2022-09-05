package com.dev6.post

import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.core.util.ImageUpload
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.post.bottom.AgeBottomSeatFragment
import com.dev6.post.bottom.GenderBottomSeatFragment
import com.dev6.post.databinding.FragmentPetAdoptPostBinding
import com.dev6.post.item.ItemChoiceAnimal
import com.dev6.post.item.ItemSerchAnimal
import com.dev6.post.viewmodel.AdoptPostViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedImagePicker
import java.text.SimpleDateFormat

@AndroidEntryPoint
class PetAdoptPostFragment :
    BindingFragment<FragmentPetAdoptPostBinding>(R.layout.fragment_pet_adopt_post) {

    lateinit var imageUpload: ImageUpload
    private val adoptPostViewModel: AdoptPostViewModel by activityViewModels()
    private val genderBottomSeatFragment by lazy { GenderBottomSeatFragment() }
    private val ageBottomSeatFragment by lazy { AgeBottomSeatFragment() }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    override fun initView() {
        super.initView()
        val choiceAdapter = GroupieAdapter()
        imageUpload = ImageUpload()
        binding.rvAnimalChoice.adapter = choiceAdapter


        binding.include.tvTop.text = resources.getString(R.string.title_adaption_insert)
        //todo 이것도 깔끔하게 정리하기
        choiceAdapter.add(ItemChoiceAnimal("믹스견"))
        choiceAdapter.add(ItemChoiceAnimal("치와와"))
        choiceAdapter.add(ItemChoiceAnimal("골든 리트리버"))
        choiceAdapter.add(ItemChoiceAnimal("말티즈"))
        choiceAdapter.add(ItemSerchAnimal())

        binding.cvEndDate.setHint("종료일")
    }

    override fun initViewModel() {
        super.initViewModel()

        repeatOnStarted {
            adoptPostViewModel.adoptChoizceStateFlow.collect {
                binding.btnChoiceGender.setText(it.gender)
                binding.btnChoiceAge.setText(it.age)
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.btnChoiceGender.setOnClickListener {
            genderBottomSeatFragment.show(this.parentFragmentManager, "")
        }
        binding.btnChoiceAge.setOnClickListener {
            ageBottomSeatFragment.show(this.parentFragmentManager, "")
        }
        binding.cbBreedChoice.setOnClickListener {
            findNavController().navigate(R.id.action_petAdoptPostFragment_to_speciesChoiceFragment)
        }

        binding.include.tvLeft.setOnClickListener {
            requireActivity().finish()
        }
        binding.vCamera2.setOnClickListener {
            TedImagePicker.with(requireContext())
                .max(5, "")
                .startMultiImage { uriList ->
                }
        }

        binding.cvStartDate.setClick { excuteDatePicker() }
        binding.cvEndDate.setClick { excuteDatePicker() }
    }

    private fun excuteDatePicker() {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
                .setTitleText("날짜를 골라주세용 응애!!!")
                .build()

        dateRangePicker.addOnPositiveButtonClickListener {
            binding.cvStartDate.setText(simpleDateFormat.format(it.first))
            binding.cvEndDate.setText(simpleDateFormat.format(it.second))
        }
        dateRangePicker.show(this.parentFragmentManager, null)
    }


}