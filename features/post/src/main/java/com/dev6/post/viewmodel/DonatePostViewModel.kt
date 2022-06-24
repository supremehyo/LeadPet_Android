package com.dev6.login.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import javax.inject.Inject

@HiltViewModel
class DonatePostViewModel @Inject constructor(

) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _postImageFlow = MutableStateFlow<List<Uri>>(emptyList())
    val postImageFlow = _postImageFlow.asStateFlow()

    fun setPostImage(uriList: List<Uri>) {
        _postImageFlow.value = uriList
    }

    sealed class Event {
//        data class JoinEvent(
//            val loginDto: LoginEntitiy
//        ) : Event()
//
//        data class ErrorEvent(
//            val text: String
//        ) : Event()
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
//
//    fun insertLifePost(text: String, content: String) {
//        viewModelScope.launch {
//
//        }
//    }

}