package com.dev6.post

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.base.BindingFragment
import com.dev6.core.base.UiState
import com.dev6.core.util.extension.getKeyFirst
import com.dev6.core.util.extension.setClickEvent
import com.dev6.core.view.CenterSmoothScroller
import com.dev6.domain.entitiyRepo.IndexBreed
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
    private var isClicked: Boolean = false

    private val sectionMap: HashMap<Section, String> = hashMapOf()
    private val IndexMap: HashMap<String, View> = hashMapOf()

    override fun initView() {
        super.initView()
        initIndexView()

    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStartedFragment {
            speicesViewModel.speciesStateFlow.collectLatest {
                uiHandler(it)
            }
        }
        repeatOnStartedFragment {
            speicesViewModel.indexStateFlow.collectLatest { index ->
                clearIndexColor()
                changeIndexColor(IndexMap.get(index), R.color.Main)
            }
        }
    }

    private fun uiHandler(uiState: UiState<List<IndexBreed>>) {
        when (uiState) {
            is UiState.Loding -> {

            }

            is UiState.Success -> {
                clearView()
                uiState.data.extractIndex().let { addIndexView(it) }
                uiState.data.forEach { species ->
                    val speciesSection = Section().also { section ->
                        section.setHeader(ItemIndex(species.index))
                        section.addAll(species.breedList.map {
                            ItemListPet(it) { breedName->

                            }
                        })
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
        //todo getCount 생기기 전까진
        binding.chipGroup.setOnClickListener {
            when (binding.chipGroup.checkedChipId) {
                R.id.c_all -> binding.cAll.text.toString()
                R.id.c_cat -> binding.cCat.text.toString()
                else -> null
            }
        }

    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun addIndexView(indexList: List<String>) {
        val inflater = LayoutInflater.from(binding.root.context)
        indexList.forEachIndexed { index, item ->
            val itemView = inflater.inflate(R.layout.view_index, null, false).also {
                val view = it.findViewById<TextView>(R.id.tv_index)
                view.text = item
            }
            itemView.setClickEvent(viewLifecycleOwner.lifecycleScope) {
                speicesViewModel.setIndex(item)
                isClicked = true
                scrollSectionPosition(item)
            }
            IndexMap.set(item, itemView)
            binding.llAlpabet.addView(itemView)
            if (index == 0) speicesViewModel.setIndex(item)
        }

    }

    private fun clearView() {
        binding.llAlpabet.removeAllViews()
        sectionMap.clear()
        breedAdapter.clear()

    }

    /**
     * 인덱스뷰 만드는 설정
     *
     */
    private fun initIndexView() {
        breedAdapter = GroupieAdapter().also {
            binding.rvPetList.adapter = it
            binding.rvPetList.addOnScrollListener(
                VisiblePositionChangeListener(
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
                            if (isClicked) return
                            speicesViewModel.setIndex(
                                sectionMap[breedAdapter.getGroupAtAdapterPosition(
                                    position
                                )] ?: return
                            )
                        }

                        override fun onLastInvisiblePositionChanged(position: Int) {}
                    })
            )
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

    /**
     * 해당 섹션으로 포지션 이동
     * todo 스무스하게 이동하는건 다음 버전에 추가
     * @param item
     */
    private fun scrollSectionPosition(item: String) {
        val section = sectionMap.getKeyFirst(item)
        val layoutManager = binding.rvPetList.layoutManager as LinearLayoutManager
        breedAdapter.getAdapterPosition(section).also {
            layoutManager.scrollToPositionWithOffset(it, 0)
        }

        isClicked = false
    }
}