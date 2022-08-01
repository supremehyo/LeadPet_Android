package com.dev6.post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.LifePost
import com.dev6.domain.entitiyRepo.Species
import com.dev6.domain.usecase.post.GetSpeciesListBaseUseCase
import com.dev6.domain.usecase.post.GetSpeciesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeicesViewModel @Inject constructor(
    private val getSpeciesListUseCase: @JvmSuppressWildcards GetSpeciesListBaseUseCase
) : ViewModel() {

    private val _speciesStateFlow = MutableStateFlow<UiState<List<Species>>>(UiState.Loding)
    val speciesStateFlow = _speciesStateFlow.asStateFlow()

    private val _indexStateFlow = MutableStateFlow<String>("")
    val indexStateFlow = _indexStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getSpeciesListUseCase(Unit).collect { uiState ->
                _speciesStateFlow.update { uiState }
            }
        }
    }

    fun setIndex(index: String) {
        _indexStateFlow.update { index }
    }

}