package com.dev6.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.ProfileUserRepo
import com.dev6.domain.model.ProfileUserUpdateRepo
import com.dev6.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val profileRepoUseCase: ProfileRepoUseCase
) : ViewModel() {

    private val _eventProfileDetail = MutableEventFlow<Event>()
    val eventProfileDetail = _eventProfileDetail.asEventFlow()

    private val _eventProfileUpdateDetail = MutableEventFlow<Event>()
    val eventProfileUpdateDetail = _eventProfileUpdateDetail.asEventFlow()

    ///프로필 데이터 가져오기
    fun eventShelterProfileDetailData(event :Event){
        viewModelScope.launch {
            _eventProfileDetail.emit(event)
        }
    }
    fun getShelterProfileDetailData(userId :String) = viewModelScope.launch{
        profileRepoUseCase.getShelterProfileDetailData(userId).collect{ uiState->
            eventShelterProfileDetailData(Event.ProfileUiEvent(uiState))
        }
    }

    //프로필 유저 데이터 업데이트 하기
    fun eventUpdateShelterProfileData(event :Event){
        viewModelScope.launch {
            _eventProfileUpdateDetail.emit(event)
        }
    }
    fun updateShelterProfileData(dto: ProfileUserUpdateRepo ,userId :String) = viewModelScope.launch{
        profileRepoUseCase.updateShelterProfileDetailData(dto ,userId).collect{ uiState->
            eventShelterProfileDetailData(Event.ProfileUpdateUiEvent(uiState))
        }
    }


    sealed class Event{
        data class ProfileUiEvent(val uiState: UiState<ProfileUserRepo>) : Event()
        data class ProfileUpdateUiEvent(val uiState: UiState<ResponseBody>) : Event()
    }
}