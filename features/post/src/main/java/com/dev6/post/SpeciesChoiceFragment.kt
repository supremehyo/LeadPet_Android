package com.dev6.post

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.entitiyRepo.Species
import com.dev6.domain.entitiyRepo.extractIndex
import com.dev6.post.databinding.FragmentSpeciesChoiceBinding
import com.dev6.post.item.ItemIndex
import com.dev6.post.item.ItemListPet
import com.dev6.post.item.VisiblePositionChangeListener
import com.dev6.post.viewmodel.SpeicesViewModel
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SpeciesChoiceFragment :
    BindingFragment<FragmentSpeciesChoiceBinding>(R.layout.fragment_species_choice) {

    private val speicesViewModel: SpeicesViewModel by activityViewModels()
    lateinit var breedAdapter: GroupieAdapter

    private val sectionMap: HashMap<Section, String> = hashMapOf()
    private val IndexMap: HashMap<String, View> = hashMapOf()

    override fun initView() {
        super.initView()
        breedAdapter = GroupieAdapter().also {
            binding.rvPetList.adapter = it
            binding.rvPetList.addOnScrollListener(VisiblePositionChangeListener(
                binding.rvPetList.layoutManager as LinearLayoutManager,
                object : VisiblePositionChangeListener.OnChangeListener {
                    override fun onFirstVisiblePositionChanged(position: Int) {
                        speicesViewModel.setIndex(
                            sectionMap[breedAdapter.getGroupAtAdapterPosition(
                                position
                            )] ?: return
                        )
                    }

                    override fun onLastVisiblePositionChanged(position: Int) {}

                    override fun onFirstInvisiblePositionChanged(position: Int) {
//                        Timber.d(sectionMap[breedAdapter.getGroupAtAdapterPosition(position)])
                        speicesViewModel.setIndex(
                            sectionMap[breedAdapter.getGroupAtAdapterPosition(
                                position
                            )] ?: return
                        )
                    }

                    override fun onLastInvisiblePositionChanged(position: Int) {}
                }
            ))
        }

    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            speicesViewModel.speciesStateFlow.collectLatest {
                uiHandler(it)
            }
        }
        repeatOnStarted {
            speicesViewModel.indexStateFlow.collectLatest { index ->
                clearIndexColor()
                changeIndexColor(IndexMap.get(index), R.color.Main)
            }
        }
    }

    private fun changeIndexColor(indexView: View?, @ColorRes color: Int) {
        indexView?.findViewById<TextView>(R.id.tv_index)?.also {
            it.setTextColor(resources.getColor(color, null))
        }
    }

    private fun clearIndexColor() {
        IndexMap.forEach { key, value ->
            changeIndexColor(value, R.color.color_bd)
        }
    }

    private fun uiHandler(uiState: UiState<List<Species>>) {
        when (uiState) {
            is UiState.Loding -> {

            }

            is UiState.Success -> {
                sectionMap.clear()

                uiState.data.extractIndex().let { addIndexView(it) }

                uiState.data.forEach { species ->
                    val speciesSection = Section().also { section ->
                        section.setHeader(ItemIndex(species.index))
                        section.addAll(species.speciesList.map { ItemListPet(it) })
                    }
                    breedAdapter.add(speciesSection)
                    sectionMap[speciesSection] = species.index

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
            itemView.setOnClickListener {
                speicesViewModel.setIndex(item)
                scrollSectionPosition(item)
            }
            IndexMap.set(item, itemView)
            binding.llAlpabet.addView(itemView)
        }

    }

    private fun scrollSectionPosition(item: String) {
    sectionMap.
    }
}