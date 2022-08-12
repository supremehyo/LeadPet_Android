package com.dev6.feed.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.entitiyRepo.ShelterEntitiyRepo
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.domain.usecase.AdoptPagingRepoUseCase
import com.dev6.domain.usecase.DailyPagingRepoUseCase
import com.dev6.domain.usecase.DonationPagingRepoUseCase
import com.dev6.domain.usecase.ShelterPagingRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
@Inject constructor(
    private val pagingRepoUseCase: DailyPagingRepoUseCase,
    private val donationRepoUserCase: DonationPagingRepoUseCase,
    private val adoptRepoUserCase: AdoptPagingRepoUseCase,
    private val shelterUserCase: ShelterPagingRepoUseCase
) : ViewModel() {

    private val _spinnerEntry = MutableStateFlow(emptyList<String>())
    val spinnerEntry: StateFlow<List<String>?> = _spinnerEntry
    val spinnerData = MutableStateFlow<String>("")

    val _viewNameData = MutableStateFlow<FeedViewType>(FeedViewType.HOME)
    val viewNameData: StateFlow<FeedViewType> = _viewNameData

    private val _donationPagingFlow = MutableSharedFlow<PagingData<DonationPostFeed>>()
    val donationPagingFlow = _donationPagingFlow.asSharedFlow()


    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _eventFlow2 = MutableEventFlow<Event>()
    val eventFlow2 = _eventFlow2.asEventFlow()

    private val _eventFlow3 = MutableEventFlow<Event>()
    val eventFlow3 = _eventFlow3.asEventFlow()

    private val _eventFlow4 = MutableEventFlow<Event>()
    val eventFlow4 = _eventFlow4.asEventFlow()

    fun setSpinnerEntry(Entry: List<String>) {
        viewModelScope.launch {
            _spinnerEntry.emit(Entry)
        }
    }

    fun setCurrentView(viewName: FeedViewType) {
        viewModelScope.launch {
            _viewNameData.emit(viewName)
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    private fun event2(event: Event) {
        viewModelScope.launch {
            _eventFlow2.emit(event)
        }
    }

    private fun event3(event: Event) {
        viewModelScope.launch {
            _eventFlow3.emit(event)
        }
    }

    private fun event4(event: Event) {
        viewModelScope.launch {
            _eventFlow4.emit(event)
        }
    }

    fun getFeedList() = viewModelScope.launch {
        pagingRepoUseCase.getDailyDataPagingData().collect { uistate ->
            event(Event.DailyUiEvent(uistate))
        }
    }

    fun getAdoptList() = viewModelScope.launch {
        adoptRepoUserCase.getAdoptDataPagingData().collect { uistate ->
            event3(Event.AdoptUiEvent(uistate))
        }
    }

    fun getDonationList() = viewModelScope.launch {
        donationRepoUserCase.getDonationPagingData().collect { uistate ->
            event2(Event.DonationUiEvent(uistate))
        }
    }

    fun getNearShelterList(cityName: String, shelterName: String) = viewModelScope.launch {
        shelterUserCase.getShelterPagingData(cityName, shelterName).collect { uistate ->
            event4(Event.ShelterUiEvnet(uistate))
        }
    }

    sealed class Event {
        data class DailyUiEvent(val uiState: UiState<Flow<PagingData<DailyPostFeed>>>) : Event()
        data class DonationUiEvent(val uiState: UiState<Flow<PagingData<DonationPostFeed>>>) :
            Event()

        data class AdoptUiEvent(val uiState: UiState<Flow<PagingData<AdoptPostFeed>>>) : Event()
        data class ShelterUiEvnet(val uiState: UiState<Flow<PagingData<ShelterEntitiyRepo>>>) :
            Event()
    }
}