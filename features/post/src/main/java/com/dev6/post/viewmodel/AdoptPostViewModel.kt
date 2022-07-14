package com.dev6.post.viewmodel

import androidx.lifecycle.ViewModel
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.post.state.AdoptChoiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AdoptPostViewModel @Inject constructor(

) : ViewModel() {

    private val _adoptChoiceStateFlow =
        MutableStateFlow<AdoptChoiceState>(AdoptChoiceState("동물선택", "암수구분", "나이"))
    val adoptChoizceStateFlow = _adoptChoiceStateFlow.asStateFlow()

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

}