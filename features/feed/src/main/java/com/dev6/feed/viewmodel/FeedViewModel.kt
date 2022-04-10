package com.dev6.feed.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
@Inject constructor (): ViewModel() {

    private val _spinnerEntry = MutableStateFlow(emptyList<String>())
    val spinnerEntry : StateFlow<List<String>?> = _spinnerEntry

    val spinnerData = MutableStateFlow<String>("")


    fun setSpinnerEntry(Entry: List<String>) {
        viewModelScope.launch {
            _spinnerEntry.emit(Entry)
        }
    }


}