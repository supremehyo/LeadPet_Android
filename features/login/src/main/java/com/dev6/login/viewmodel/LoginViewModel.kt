package com.dev6.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.base.UiState
import com.dev6.core.enum.LoginType
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
class LoginViewModel @Inject constructor(private val   loginRepoUseCase : LoginRepoUseCase
) :ViewModel(){

//    private val _id = MutableSharedFlow<String>()
//    val joinDataFlow = _joinDataFlow.asSharedFlow()
    private val _loginDto = MutableStateFlow<LoginEntitiy>(LoginEntitiy(type = LoginType.email))
    val loginDto =  _loginDto.asStateFlow()

    private val _loginFlow= loginRepoUseCase.login(loginDto.value)


     fun getlogin(){
        viewModelScope.launch {
            Timber.d("로그인 들어옴")
            loginRepoUseCase.login(loginDto.value).collect {uiState->

               when(uiState) {
                   is  UiState.Success<UserEntityRepo> ->{
                       Timber.d(uiState.data.toString())
                   }
                   is UiState.Error ->{
                       Timber.d(uiState.error?.message)
                   }
                   is UiState.Loding ->{
                       Timber.d("로딩")
                   }
               }


            }

        }
    }

}