package com.dev6.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.NormalUserRepo
import com.dev6.domain.model.NormalUserUpdateRepo
import com.dev6.domain.model.ProfileUserRepo
import com.dev6.domain.model.ProfileUserUpdateRepo
import com.dev6.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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

    private val _eventNormalUserProfileDetail = MutableEventFlow<Event>()
    val eventNormalUserProfileDetail = _eventNormalUserProfileDetail.asEventFlow()


    private val _eventProfileUpdateDetail = MutableEventFlow<Event>()
    val eventProfileUpdateDetail = _eventProfileUpdateDetail.asEventFlow()

    private val _eventNormalUserProfileUpdateDetail = MutableEventFlow<Event>()
    val eventNormalUserProfileUpdateDetail = _eventNormalUserProfileUpdateDetail.asEventFlow()

    ///쉘터 프로필 디테일 이벤트
    fun eventShelterProfileDetailData(event :Event){
        viewModelScope.launch {
            _eventProfileDetail.emit(event)
        }
    }

    // 쉘터 프로필 디테일
    fun getShelterProfileDetailData(userId :String) = viewModelScope.launch{
        profileRepoUseCase.getShelterProfileDetailData(userId).collect{ uiState->
            eventShelterProfileDetailData(Event.ProfileUiEvent(uiState))
        }
    }

    // 노말 유저 프로필 디테일 이ㅔㄴ트
    fun eventNormalUserProfileDetailData(event : Event){
        viewModelScope.launch {
            _eventNormalUserProfileDetail.emit(event)
        }
    }

    //노말 유저 프로필 디테일
    fun getNormalUserProfileDetailData(userId : String) = viewModelScope.launch {
        profileRepoUseCase.getNormalUserProfileDetailData(userId).collect{ uiState->
            eventNormalUserProfileDetailData(Event.NormalUserProfileUiEvent(uiState))
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
            eventUpdateShelterProfileData(Event.ProfileUpdateUiEvent(uiState))
        }
    }

    //프로필 유저 데이터 업데이트 하기
    fun eventUpdateNormalUserProfileData(event :Event){
        viewModelScope.launch {
            _eventNormalUserProfileUpdateDetail.emit(event)
        }
    }

    fun updateNormalUserProfileData(dto: NormalUserUpdateRepo ,userId :String) = viewModelScope.launch{
        profileRepoUseCase.updateNormalUserProfileDetailData(dto ,userId).collect{ uiState->
            eventUpdateNormalUserProfileData(Event.ProfileUpdateUiEvent(uiState))
        }
    }




    sealed class Event{
        data class ProfileUiEvent(val uiState: UiState<ProfileUserRepo>) : Event()
        data class ProfileUpdateUiEvent(val uiState: UiState<ResponseBody>) : Event()
        data class NormalUserProfileUiEvent(val uiState: UiState<NormalUserRepo>) : Event()
        data class NormalUserProfileUpdateUiEvent(val uiState: UiState<ResponseBody>) : Event()
    }
}