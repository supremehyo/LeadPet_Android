package com.dev6.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dev6.core.base.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.ShelterEntitiyRepo
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.comment.Comment
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.Save
import com.dev6.domain.model.save.SavedAdoptionData
import com.dev6.domain.model.save.SavedDailyData
import com.dev6.domain.model.save.SavedDonationData
import com.dev6.domain.usecase.save.GetSavedAdoptionPostUseCase
import com.dev6.domain.usecase.save.GetSavedDailyPostUseCase
import com.dev6.domain.usecase.save.GetSavedDonationPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class SaveViewModel
@Inject constructor(
    private val savedDailyPostUseCase: GetSavedDailyPostUseCase,
    private val savedDonationPostUseCase: GetSavedDonationPostUseCase,
    private val savedAdoptionPostUseCase: GetSavedAdoptionPostUseCase
) : ViewModel(){

    private val _eventSavedDailyList = MutableEventFlow<SaveViewModel.Event>()
    val eventSavedDailyList = _eventSavedDailyList.asEventFlow()

    private val _eventDonationList = MutableEventFlow<SaveViewModel.Event>()
    val eventDonationList = _eventDonationList.asEventFlow()

    private val _eventAdoptionList = MutableEventFlow<SaveViewModel.Event>()
    val eventAdoptionList = _eventAdoptionList.asEventFlow()

    private fun eventSavedDailyList(event: SaveViewModel.Event) {
        viewModelScope.launch {
            _eventSavedDailyList.emit(event)
        }
    }

    private fun eventSavedAdoptionList(event: SaveViewModel.Event) {
        viewModelScope.launch {
            _eventAdoptionList.emit(event)
        }
    }

    private fun eventSavedDonationList(event: SaveViewModel.Event) {
        viewModelScope.launch {
            _eventDonationList.emit(event)
        }
    }


    fun getDailyListByUserId(userId: String) = viewModelScope.launch {
        savedDailyPostUseCase.getSavedDailyPagingData(userId).collect { uistate ->
            eventSavedDailyList(SaveViewModel.Event.SavedDailyUiEvent(uistate))
        }
    }

    fun getAdoptionListByUserId(userId: String) = viewModelScope.launch {
        savedAdoptionPostUseCase.getSavedAdoptPagingData(userId).collect { uistate ->
            eventSavedAdoptionList(SaveViewModel.Event.SavedAdoptUiEvent(uistate))
        }
    }

    fun getDonationListByUserId(userId: String) = viewModelScope.launch {
        savedDonationPostUseCase.getSavedDonationPagingData(userId).collect { uistate ->
            eventSavedDonationList(SaveViewModel.Event.SavedDonationUiEvent(uistate))
        }
    }





    sealed class Event {
        data class SavedDailyUiEvent(val uiState: UiState<Flow<PagingData<SavedDailyData>>>) : Event()
        data class SavedDonationUiEvent(val uiState: UiState<Flow<PagingData<SavedDonationData>>>) : Event()
        data class SavedAdoptUiEvent(val uiState: UiState<Flow<PagingData<SavedAdoptionData>>>) : Event()
    }


}