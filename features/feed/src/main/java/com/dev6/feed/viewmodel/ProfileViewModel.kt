package com.dev6.feed.viewmodel

import android.util.Log
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
import com.dev6.post.state.AdoptChoiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
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

    private val _eventClickProfileDetail = MutableEventFlow<Event>()
    val eventClickProfileDetail = _eventClickProfileDetail.asEventFlow()

    private val _eventNormalUserProfileDetail = MutableEventFlow<Event>()
    val eventNormalUserProfileDetail = _eventNormalUserProfileDetail.asEventFlow()


    private val _eventProfileUpdateDetail = MutableEventFlow<Event>()
    val eventProfileUpdateDetail = _eventProfileUpdateDetail.asEventFlow()

    private val _eventNormalUserProfileUpdateDetail = MutableEventFlow<Event>()
    val eventNormalUserProfileUpdateDetail = _eventNormalUserProfileUpdateDetail.asEventFlow()

    private val _cityChoiceStateFlow = MutableStateFlow<String>("서울")
    val cityChoiceStateFlow = _cityChoiceStateFlow.asStateFlow()

    private val _userUpdateImageFlow = MutableStateFlow<List<ByteArray>>(emptyList())
    val userUpdateImageFlow = _userUpdateImageFlow.asStateFlow()

    fun setProfileImage(imageList: List<ByteArray>) {
        _userUpdateImageFlow.update { imageList }
    }


    ///쉘터 프로필 디테일 이벤트
    fun eventShelterProfileDetailData(event :Event){
        viewModelScope.launch {
            _eventProfileDetail.emit(event)
        }
    }

    ///쉘터 프로필 디테일 이벤트
    fun eventClickShelterProfileDetailData(event :Event){
        viewModelScope.launch {
            _eventClickProfileDetail.emit(event)
        }
    }

    // 쉘터 프로필 디테일
    fun getShelterProfileDetailData(userId :String) = viewModelScope.launch{
        profileRepoUseCase.getShelterProfileDetailData(userId).collect{ uiState->
            eventShelterProfileDetailData(Event.ProfileUiEvent(uiState))
        }
    }

    // 쉘터 프로필 디테일
    fun getClickShelterProfileDetailData(userId :String) = viewModelScope.launch{
        profileRepoUseCase.getShelterProfileDetailData(userId).collect{ uiState->
            eventClickShelterProfileDetailData(Event.ProfileUiEvent(uiState))
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

    fun updateCitySelection(city: String) {
        _cityChoiceStateFlow.value = city
    }

    //프로필 유저 데이터 업데이트 하기
    fun eventUpdateNormalUserProfileData(event :Event){
        viewModelScope.launch {
            _eventNormalUserProfileUpdateDetail.emit(event)
        }
    }

    fun updateNormalUserProfileData(dto: NormalUserUpdateRepo ,userId :String) = viewModelScope.launch{
        Log.v("sdfasdf" , userUpdateImageFlow.value.toString())
        dto.copy(imageList = userUpdateImageFlow.value)
        profileRepoUseCase.updateNormalUserProfileDetailData(dto ,userId).collect{ uiState->
            eventUpdateNormalUserProfileData(Event.NormalUserProfileUpdateUiEvent(uiState))
        }
    }




    sealed class Event{
        data class ProfileUiEvent(val uiState: UiState<ProfileUserRepo>) : Event()
        data class ProfileUpdateUiEvent(val uiState: UiState<ResponseBody>) : Event()
        data class NormalUserProfileUiEvent(val uiState: UiState<NormalUserRepo>) : Event()
        data class NormalUserProfileUpdateUiEvent(val uiState: UiState<ResponseBody>) : Event()
    }
}