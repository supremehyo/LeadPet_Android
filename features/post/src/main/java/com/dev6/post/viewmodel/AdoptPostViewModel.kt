package com.dev6.post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.core.enum.AnimalType
import com.dev6.core.enum.Gender
import com.dev6.core.enum.Neutering
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.Breed
import com.dev6.domain.model.IndexBreed
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.usecase.post.GetSpeciesListBaseUseCase
import com.dev6.domain.usecase.post.InsertAdoptPostBaseUseCase
import com.dev6.post.state.AdoptChoiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdoptPostViewModel @Inject constructor(
    private val getSpeciesListUseCase: @JvmSuppressWildcards GetSpeciesListBaseUseCase,
    private val InsertAdoptPostUseCase: @JvmSuppressWildcards InsertAdoptPostBaseUseCase
) : ViewModel() {

    private val _postImageFlow = MutableStateFlow<List<ByteArray>>(emptyList())
    val postImageFlow = _postImageFlow.asStateFlow()

    private val _titleStateFlow = MutableStateFlow<String>("")
    val titleStateFlow = _titleStateFlow.asStateFlow()

    private val _contentStateFlow = MutableStateFlow<String>("")
    val contentStateFlow = _contentStateFlow.asStateFlow()

    private val _adoptChoiceStateFlow =
        MutableStateFlow<AdoptChoiceState>(
            AdoptChoiceState(
                Gender.DEFAULT,
                "나이",
                Neutering.NONE
            )
        )
    val adoptChoiceStateFlow = _adoptChoiceStateFlow.asStateFlow()

    private val _speciesListStateFlow = MutableStateFlow<UiState<List<IndexBreed>>>(UiState.Loding)
    val speciesListStateFlow = _speciesListStateFlow.asStateFlow()

    private val _indexStateFlow = MutableStateFlow<String>("")
    val indexStateFlow = _indexStateFlow.asStateFlow()

    private val _speciesStateFlow = MutableStateFlow<Breed>(Breed("품종선택", AnimalType.NONE))
    val speciesStateFlow = _speciesStateFlow.asStateFlow()

    private val _eventFlow = MutableEventFlow<AdoptPostViewModel.Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun getSpeciesList() = viewModelScope.launch {
        getSpeciesListUseCase(Unit).collect { uiState ->
            _speciesListStateFlow.update { uiState }
        }
    }

    fun setIndex(index: String) {
        _indexStateFlow.update { index }
    }

    fun updateGenderSelection(gender: Gender) {
        _adoptChoiceStateFlow.update { currentState ->
            currentState.copy(gender = gender)
        }
    }

    fun updateNeuteringSelection(neutering: Neutering) {
        _adoptChoiceStateFlow.update { currentState ->
            currentState.copy(neutering = neutering)
        }
    }

    private fun event(event: AdoptPostViewModel.Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun updateAgeSelection(age: String) {
        _adoptChoiceStateFlow.update { currentState ->
            currentState.copy(age = age)
        }
    }

    fun updateTitle(text: String) {
        _titleStateFlow.update { text }
    }

    fun updateContent(text: String) {
        _contentStateFlow.update { text }
    }

    fun insertAdoptPost() = viewModelScope.launch {
        val repo = AdoptPostFeed(
            age = adoptChoiceStateFlow.value.age.toIntOrNull(),
            animalType = speciesStateFlow.value.animalType,
            contents = contentStateFlow.value,
            endDate = listOf(),
            euthanasiaDate = "",
            gender = adoptChoiceStateFlow.value.gender,
            images = listOf(),
            neutering = adoptChoiceStateFlow.value.neutering,
            species = speciesStateFlow.value.breedName,
            startDate = listOf(),
            title = titleStateFlow.value,
            userId = "",
            imageByteArrayList = listOf(),
            adoptionPostId = ""
        )

        InsertAdoptPostUseCase(repo).collect { uiState ->
            event(AdoptPostViewModel.Event.UiEvent(uiState))
        }
    }

    fun updateSpecies(breed: Breed) = _speciesStateFlow.update { _ -> breed }

    fun updatePostImage(imageList: List<ByteArray>) {
        _postImageFlow.update { imageList }
    }

    sealed class Event {
        data class UiEvent(
            val uiState: UiState<AdoptPostFeed>
        ) : Event()
    }
}
