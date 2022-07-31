package com.dev6.post

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.entitiyRepo.Species
import com.dev6.domain.entitiyRepo.extractIndex
import com.dev6.login.viewmodel.DonatePostViewModel
import com.dev6.post.databinding.FragmentSpeciesChoiceBinding
import com.dev6.post.item.AlpabetGroupieAdapter
import com.dev6.post.item.ItemIndex
import com.dev6.post.item.ItemListPet
import com.dev6.post.viewmodel.AdoptPostViewModel
import com.dev6.post.viewmodel.SpeicesViewModel
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SpeciesChoiceFragment :
    BindingFragment<FragmentSpeciesChoiceBinding>(R.layout.fragment_species_choice) {

    private val speicesViewModel: SpeicesViewModel by activityViewModels()
    lateinit var breedAdapter: AlpabetGroupieAdapter

    override fun initView() {
        super.initView()
        breedAdapter = AlpabetGroupieAdapter().also {
            binding.rvPetList.adapter = it
        }

    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            speicesViewModel.speciesStateFlow.collectLatest {
                uiHandler(it)
            }
        }
    }

    private fun uiHandler(uiState: UiState<List<Species>>) {
        when (uiState) {
            is UiState.Loding -> {

            }

            is UiState.Success -> {
                uiState.data.extractIndex().let { addIndexView(it) }
                //하나의 섹션을 열고, 담는다.
                uiState.data.forEach { species ->
                    val speciesSection = Section().also { section ->
                        section.setHeader(ItemIndex(species.index))
                        section.addAll(species.speciesList.map { ItemListPet(it) })

                    }
                    breedAdapter.add(speciesSection)

                }

            }
            is UiState.Error -> {

            }

        }

    }

    override fun initListener() {
        super.initListener()

    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun addIndexView(indexList: List<String>) {
        val inflater = LayoutInflater.from(binding.root.context)
        indexList.forEach { item ->
            val itemView = inflater.inflate(R.layout.view_index, null, false).also {
                val view = it.findViewById<TextView>(R.id.tv_index)
                view.text = item
            }
            binding.llAlpabet.addView(itemView)
        }

    }
}