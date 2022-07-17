package com.dev6.feed.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.data.entity.daily.DailyFeedEntitiy
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.domain.usecase.AdoptPagingRepoUseCase
import com.dev6.domain.usecase.DailyPagingRepoUseCase
import com.dev6.domain.usecase.DonationPagingRepoUseCase
import com.dev6.domain.usecase.PagingRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
@Inject constructor(
    private val pagingRepoUseCase: DailyPagingRepoUseCase
    ,private val donationRepoUserCase: DonationPagingRepoUseCase,
    private val adoptRepoUserCase: AdoptPagingRepoUseCase
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

    //홈화면 기부 추천
    fun getRecommendDonationList() {

    }

    //홈화면 피드 추천
    fun getRecommendFeedList() {

    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun getFeedList()= viewModelScope.launch {
        pagingRepoUseCase.getDailyDataPagingData().collect{ uistate ->
            event(Event.DailyUiEvent(uistate))
        }
    }

    fun getAdoptList()= viewModelScope.launch {
        adoptRepoUserCase.getAdoptDataPagingData().collect{ uistate ->
            event(Event.AdoptUiEvent(uistate))
        }
    }
    fun getDonationList() = viewModelScope.launch {
        donationRepoUserCase.getDonationPagingData().collect{ uistate ->
            event(Event.DonationUiEvent(uistate))
        }
    }

    sealed class Event {
        data class DailyUiEvent(val uiState: UiState<Flow<PagingData<DailyPostFeed>>>) : Event()
        data class DonationUiEvent(val uiState: UiState<Flow<PagingData<DonationPostFeed>>>) : Event()
        data class AdoptUiEvent(val uiState: UiState<Flow<PagingData<AdoptPostFeed>>>) : Event()
    }
}