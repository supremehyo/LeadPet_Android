package com.dev6.feed.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dev6.core.enums.FeedViewType
import com.dev6.data.entity.DailyFeedEntitiy
import com.dev6.domain.usecase.PagingRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
@Inject constructor(private val pagingRepoUseCase: PagingRepoUseCase) : ViewModel() {

    private val _spinnerEntry = MutableStateFlow(emptyList<String>())
    val spinnerEntry: StateFlow<List<String>?> = _spinnerEntry
    val spinnerData = MutableStateFlow<String>("")

    val _viewNameData = MutableStateFlow<FeedViewType>(FeedViewType.HOME)
    val viewNameData: StateFlow<FeedViewType> = _viewNameData

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

}