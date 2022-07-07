package com.dev6.post.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.entitiyRepo.LifePost
import com.dev6.domain.usecase.post.InsertLifePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LifePostViewModel @Inject constructor(
    private val insertLifePostUseCase: InsertLifePostUseCase
) : ViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _postImageFlow = MutableStateFlow<List<Uri>>(emptyList())
    val postImageFlow = _postImageFlow.asStateFlow()

    fun setPostImage(uriList: List<Uri>) {
        _postImageFlow.value = uriList
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun insertLifePost(text: String, content: String, img: List<String>) {
        viewModelScope.launch {
            val repo = LifePost(userId = "", title = text, contents = content, images = img)
            insertLifePostUseCase(repo).collect { uiState ->
                event(Event.UiEvent(uiState))
            }
        }
    }

    sealed class Event {
        data class UiEvent(
            val uiState: UiState<LifePost>
        ) : Event()
    }
}