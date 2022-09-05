package com.dev6.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.core.enums.LoginType
import com.dev6.core.exception.NotCorrectException
import com.dev6.core.exception.ServerFailException
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.Login
import com.dev6.domain.model.User
import com.dev6.domain.usecase.login.GetLoginAccessUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepoUseCase: GetLoginAccessUseCase
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<Login>(Login(loginMethod = LoginType.EMAIL))
    val loginStateFlow = _loginStateFlow.asStateFlow()

    private val _lodingFlow = MutableStateFlow<Boolean>(false)
    val lodingFlow = _lodingFlow.asStateFlow()

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun setLogin(loginData: Login) {
        _loginStateFlow.value = loginData
    }

    fun getlogin() {
        viewModelScope.launch {
            loginRepoUseCase(loginStateFlow.value).collect { uiState ->
                when (uiState) {
                    is UiState.Success<User> -> {
                        _lodingFlow.value = false
                        Timber.d(uiState.data.toString())
                    }
                    is UiState.Error -> {
                        _lodingFlow.value = false
                        Timber.d(uiState.error?.message)

                        when (uiState.error) {
                            is NotCorrectException -> event(Event.JoinEvent(loginStateFlow.value))
                            is ServerFailException -> event(
                                Event.ErrorEvent(
                                    "계정을 찾을수 없습니다.",
                                    loginStateFlow.value
                                )
                            )
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
        data class LoginEvent(
            val loginData: Login
        ) : Event()

        data class JoinEvent(
            val loginData: Login
        ) : Event()

        data class ErrorEvent(
            val text: String,
            val loginData: Login
        ) : Event()
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}
