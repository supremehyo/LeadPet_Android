package com.dev6.post

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.core.enum.Neutering
import com.dev6.core.util.ImageUpload
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.post.bottom.AgeBottomSeatFragment
import com.dev6.post.bottom.GenderBottomSeatFragment
import com.dev6.post.databinding.FragmentPetAdoptPostBinding
import com.dev6.post.item.ItemChoiceAnimal
import com.dev6.post.item.ItemSearchAnimal
import com.dev6.post.viewmodel.AdoptPostViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
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
        repeatOnStarted {
            launch {
                adoptPostViewModel.adoptChoiceStateFlow.collect {
                    binding.btnChoiceGender.setText(it.gender.item)
                    binding.btnChoiceAge.setText(it.age)
                    when (it.neutering) {
                        Neutering.YES -> binding.btnYesNeutering.isChecked = true
                        Neutering.NO -> binding.btnNoNeutering.isChecked = true
                        Neutering.NONE -> {}
                    }
                }
            }
            launch {
                adoptPostViewModel.speciesStateFlow.collectLatest { result ->
                    binding.cbBreedChoice.setText(result.breedName)
                }
            }
            launch {
                adoptPostViewModel.titleStateFlow.collectLatest { result ->
                    binding.tvTitle.setText(result)
                }
            }

            launch {
                adoptPostViewModel.contentStateFlow.collectLatest { result ->
                    binding.tietIntroduce.setText(result)
                }
            }
            launch {
                adoptPostViewModel.postImageFlow.collect { urlList ->
                    if (urlList.isNotEmpty()) {
                        Glide.with(this@PetAdoptPostFragment).load(urlList[0])
                            .into(binding.vCamera2.getImage())
                    }
                }
            }
        }
    }

    override fun initView() {
        super.initView()
        val choiceAdapter = GroupieAdapter()
        binding.rvAnimalChoice.adapter = choiceAdapter

        binding.include.tvTop.text = resources.getString(R.string.title_adaption_insert)
        // todo 이것도 깔끔하게 정리하기
        choiceAdapter.add(ItemChoiceAnimal("믹스견"))
        choiceAdapter.add(ItemChoiceAnimal("치와와"))
        choiceAdapter.add(ItemChoiceAnimal("골든 리트리버"))
        choiceAdapter.add(ItemChoiceAnimal("말티즈"))
        choiceAdapter.add(ItemSearchAnimal())

        binding.cvEndDate.setHint("종료일")
    }

    override fun initViewModel() {
        super.initViewModel()
        if (adoptPostViewModel.postImageFlow.value.isEmpty()) {
            TedImagePicker.with(requireContext()).max(5, "더이상 사진을 넣을수 없습니다")
                .startMultiImage { uriList ->
                    val imageList: List<ByteArray> =
                        uriList.map { ImageUpload.convertUrlToBitmap(it, this.requireContext()) }
                            .map { ImageUpload.compressBitmap(it) }

                    adoptPostViewModel.updatePostImage(imageList)
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

        binding.btgNeutering.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when (checkedId) {
                R.id.btn_yes_neutering -> if (isChecked) adoptPostViewModel.updateNeuteringSelection(
                    Neutering.YES
                )
                R.id.btn_no_neutering -> if (isChecked) adoptPostViewModel.updateNeuteringSelection(
                    Neutering.NO
                )
            }
        }

        binding.include.tvLeft.setOnClickListener {
            requireActivity().finish()
        }
        binding.vCamera2.setOnClickListener {
            val galleryFragmentDirections =
                LifePostFragmentDirections.actionLifePostFragmentToGallraryFragment(
                    adoptPostViewModel.postImageFlow.value.map {
                        it.toString()
                    }.toTypedArray()
                )

            findNavController().navigate(galleryFragmentDirections)
        }

        binding.tvTitle.addTextChangedListener {
            adoptPostViewModel.updateTitle(it.toString())
        }

        binding.tietIntroduce.addTextChangedListener {
            adoptPostViewModel.updateContent(it.toString())
        }

        binding.cvStartDate.setClick { executePostingPeriodPicker() }
        binding.cvEndDate.setClick { executePostingPeriodPicker() }
        binding.cvEuthanasiaDate.setClick { executeEuthanasiaDatePicker() }
    }

    private fun executePostingPeriodPicker() {
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

    private fun executeEuthanasiaDatePicker() {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateRangePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
                .setTitleText("날짜를 골라주세용 응애!!!")
                .build()

        dateRangePicker.addOnPositiveButtonClickListener {
            Timber.tag("datapicker").d(it.toString())
            binding.cvEuthanasiaDate.setText(simpleDateFormat.format(it))
        }
        dateRangePicker.show(this.parentFragmentManager, null)
    }
}
