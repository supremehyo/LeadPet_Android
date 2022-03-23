package com.dev6.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.core.enum.LoginType
import com.dev6.core.exception.NotFoundException
import com.dev6.core.util.EventFlow
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.data.entity.LoginEntitiy
import com.dev6.domain.entitiyRepo.UserEntityRepo
import com.dev6.domain.usecase.LoginRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepoUseCase: LoginRepoUseCase
) : ViewModel() {

    //    private val _id = MutableSharedFlow<String>()
//    val joinDataFlow = _joinDataFlow.asSharedFlow()
    private val _loginDto = MutableStateFlow<LoginEntitiy>(LoginEntitiy(type = LoginType.email))
    val loginDto = _loginDto.asStateFlow()

    private val _lodingFlow = MutableStateFlow<Boolean>(false)
    val lodingFlow = _lodingFlow.asStateFlow()

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()




    fun getlogin() {
        viewModelScope.launch {
            Timber.d("로그인 들어옴")
            loginRepoUseCase.login(loginDto.value).collect { uiState ->

                when (uiState) {
                    is UiState.Success<UserEntityRepo> -> {
                        _lodingFlow.value = false
                        Timber.d(uiState.data.toString())
                    }
                    is UiState.Error -> {
                        _lodingFlow.value = false
                        Timber.d(uiState.error?.message)
                        when(uiState.error){
                            is NotFoundException ->{
                                event(Event.JoinEvent("계정을 찾을수 없습니다."))
                            }
                        }

                    }
                    is UiState.Loding -> {
                        Timber.d("로딩")
                        _lodingFlow.value = true
                    }
                }


            }

        }
    }

    sealed class Event {
        data class JoinEvent(
            val text: String
        ) : Event()
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }


}