package com.dev6.join.viewmodel
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.Join
import com.dev6.domain.usecase.login.JoinReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel
@Inject constructor (private val joinReposUseCase: JoinReposUseCase) : ViewModel(){

    private val _joinDataFlow = MutableSharedFlow<String>()
    val joinDataFlow = _joinDataFlow.asSharedFlow()

    private val _joinEvnetFlow = MutableEventFlow<Event>()
    val joinEvnetFlow = _joinEvnetFlow.asEventFlow()

    private val _joinImageFlow = MutableStateFlow<List<Uri>>(emptyList())
    val joinImageFlow = _joinImageFlow.asStateFlow()


    private val _joinDTOData = MutableSharedFlow<Join>(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val joinDTOData =  _joinDTOData.asSharedFlow()



    private fun event(event: Event) {
        viewModelScope.launch {
            _joinEvnetFlow.emit(event)
        }
    }
    fun setJoinImage(uriList: List<Uri>) {
        _joinImageFlow.value = uriList
    }

    fun initJoinImage(){
        _joinImageFlow.value = emptyList()
    }


    fun signUp(joinEntitiyRepo: Join) {
        viewModelScope.launch {
            joinReposUseCase(joinEntitiyRepo).collect{uiState ->
                event(Event.UiEvent(uiState))
            }
        }
    }

    fun setJoinDTOData(dto : Join){
        viewModelScope.launch {
            _joinDTOData.emit(dto)
        }
    }

    sealed class Event {
        data class UiEvent(
            val uiState: UiState<String>
        ) : Event()
    }
}