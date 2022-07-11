package com.dev6.feed.viewmodel


import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.data.entity.DailyFeedEntitiy
import com.dev6.domain.entitiyRepo.AdoptPostFeed
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.usecase.DonationPagingRepoUseCase
import com.dev6.domain.usecase.PagingRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
@Inject constructor(
    private val pagingRepoUseCase: PagingRepoUseCase
    ,private val donationRepoUserCase: DonationPagingRepoUseCase) : ViewModel() {

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

    fun getFeedList(): Flow<PagingData<DailyFeedEntitiy>> {
        return pagingRepoUseCase.getPagingData()
            .cachedIn(viewModelScope) as Flow<PagingData<DailyFeedEntitiy>>
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

    fun getAdoptList(): Flow<PagingData<DailyFeedEntitiy>> {
        return pagingRepoUseCase.getPagingData()
            .cachedIn(viewModelScope) as Flow<PagingData<DailyFeedEntitiy>>
    }


    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }


    fun getDonationList() = viewModelScope.launch {
        donationRepoUserCase.getDonationPagingData().collect{ uistate ->
            event(Event.DonationUiEvent(uistate))
        }
    }

    sealed class Event {
        data class DailyUiEvent(val uiState: UiState<PagingData<DailyFeedEntitiy>>) : Event()
        data class DonationUiEvent(val uiState: UiState<Flow<PagingData<DonationPostFeed>>>) : Event()
        data class AdoptUiEvent(val uiState: UiState<PagingData<AdoptPostFeed>>) : Event()
    }
}