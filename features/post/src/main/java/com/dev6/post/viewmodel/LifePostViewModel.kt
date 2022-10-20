package com.dev6.post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.UserData
import com.dev6.core.base.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.daily.DailyPostRequest
import com.dev6.domain.usecase.post.InsertDailyPostBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LifePostViewModel @Inject constructor(
    private val insertDailyPostBaseUseCase: @JvmSuppressWildcards InsertDailyPostBaseUseCase
) : ViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _postImageFlow = MutableStateFlow<List<ByteArray>>(emptyList())
    val postImageFlow = _postImageFlow.asStateFlow()

    fun setPostImage(imageList: List<ByteArray>) {
        _postImageFlow.update { imageList }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun insertLifePost(text: String, content: String) = viewModelScope.launch {
        val repo = DailyPostRequest(
            contents = content,
            imageByteList = postImageFlow.value,
            title = text,
            images = null,
            userId = UserData.userId
        )
        insertDailyPostBaseUseCase(repo).collect { uiState ->
            event(Event.UiEvent(uiState))
        }
    }

    sealed class Event {
        data class UiEvent(
            val uiState: UiState<DailyPost>
        ) : Event()
    }
}
