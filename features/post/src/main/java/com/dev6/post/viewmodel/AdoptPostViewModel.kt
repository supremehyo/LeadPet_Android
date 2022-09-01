package com.dev6.post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.domain.model.IndexBreed
import com.dev6.domain.usecase.post.GetSpeciesListBaseUseCase
import com.dev6.post.state.AdoptChoiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdoptPostViewModel @Inject constructor(
    private val getSpeciesListUseCase: @JvmSuppressWildcards GetSpeciesListBaseUseCase
) : ViewModel() {

    private val _adoptChoiceStateFlow = MutableStateFlow<AdoptChoiceState>(AdoptChoiceState("동물선택", "암수구분", "나이"))
    val adoptChoizceStateFlow = _adoptChoiceStateFlow.asStateFlow()

    private val _speciesListStateFlow = MutableStateFlow<UiState<List<IndexBreed>>>(UiState.Loding)
    val speciesListStateFlow = _speciesListStateFlow.asStateFlow()

    private val _indexStateFlow = MutableStateFlow<String>("")
    val indexStateFlow = _indexStateFlow.asStateFlow()

    private val _speciesStateFlow = MutableStateFlow<String>("품종선택")
    val speciesStateFlow = _indexStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getSpeciesListUseCase(Unit).collect { uiState ->
                _speciesListStateFlow.update { uiState }
            }
        }
    }

    fun setIndex(index: String) {
        _indexStateFlow.update { index }
    }

    fun updateAnimalSelection(animal: String) {
        _adoptChoiceStateFlow.update { currentState ->
            currentState.copy(animal = animal)
        }
    }

    fun updateGenderSelection(gender: String) {
        _adoptChoiceStateFlow.update { currentState ->
            currentState.copy(gender = gender)
        }
    }

    fun updateAgeSelection(age: String) {
        _adoptChoiceStateFlow.update { currentState ->
            currentState.copy(age = age)
        }
    }

    fun updateSpecies(breed: String) = _speciesStateFlow.update { _ -> breed }
}
