package com.dev6.join.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.data.entity.JoinEntitiy
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.usecase.JoinReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class JoinViewModel
@Inject constructor (private val joinReposUseCase: JoinReposUseCase) : ViewModel(){

    private val _joinDataFlow = MutableSharedFlow<String>()
    val joinDataFlow = _joinDataFlow.asSharedFlow()

    private val _joinDTOData = MutableSharedFlow<JoinEntitiy>()
    val joinDTOData =  _joinDTOData.asSharedFlow()


    fun signUp(joinEntitiyRepo: JoinEntitiy) {
        viewModelScope.launch {
            joinReposUseCase.signUp(joinEntitiyRepo).collectLatest {
                _joinDataFlow.emit(it)
            }
        }
    }

    fun setJoinDTOData(dto : JoinEntitiy){
        viewModelScope.launch {
            _joinDTOData.emit(dto)
        }
    }


}