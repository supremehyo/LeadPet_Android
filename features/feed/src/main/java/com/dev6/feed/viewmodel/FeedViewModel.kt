package com.dev6.feed.viewmodel


import android.util.Log
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
import com.dev6.domain.entitiyRepo.comment.Comment
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
@Inject constructor(
    private val pagingRepoUseCase: DailyPagingRepoUseCase,
    private val donationRepoUserCase: DonationPagingRepoUseCase,
    private val adoptRepoUserCase: AdoptPagingRepoUseCase,
    private val shelterUserCase: ShelterPagingRepoUseCase,
    private val commentPagingRepoUseCase: CommentPagingRepoUseCase
) : ViewModel() {

    private val _spinnerEntry = MutableStateFlow(emptyList<String>())
    val spinnerEntry: StateFlow<List<String>?> = _spinnerEntry
    val spinnerData = MutableStateFlow<String>("")

    val _viewNameData = MutableStateFlow<FeedViewType>(FeedViewType.HOME)
    val viewNameData: StateFlow<FeedViewType> = _viewNameData

    private val _donationPagingFlow = MutableSharedFlow<PagingData<DonationPostFeed>>()
    val donationPagingFlow = _donationPagingFlow.asSharedFlow()


    private val _eventDailyList = MutableEventFlow<Event>()
    val eventDailyList = _eventDailyList.asEventFlow()

    private val _eventDonationList = MutableEventFlow<Event>()
    val eventDonationList = _eventDonationList.asEventFlow()

    private val _eventAdoptList = MutableEventFlow<Event>()
    val eventAdoptList = _eventAdoptList.asEventFlow()

    private val _eventShelterList = MutableEventFlow<Event>()
    val eventShelterList = _eventShelterList.asEventFlow()

    private val _eventFlowComment = MutableEventFlow<Event>()
    val eventFlowComment = _eventFlowComment.asEventFlow()

    private val _eventPostLike = MutableEventFlow<Event>()
    val eventPostLike = _eventPostLike.asEventFlow()

    private val _eventCommentPost = MutableEventFlow<Event>()
    val eventCommentPost = _eventCommentPost.asEventFlow()


    fun eventDailyCommentPost(event: Event){
        viewModelScope.launch {
            _eventCommentPost.emit(event)
        }
    }

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

    private fun eventDailyList(event: Event) {
        viewModelScope.launch {
            _eventDailyList.emit(event)
        }
    }

    private fun eventDonationList(event: Event) {
        viewModelScope.launch {
            _eventDonationList.emit(event)
        }
    }

    private fun eventAdoptList(event: Event) {
        viewModelScope.launch {
            _eventAdoptList.emit(event)
        }
    }

    private fun eventShelterList(event: Event) {
        viewModelScope.launch {
            _eventShelterList.emit(event)
        }
    }

    private fun eventComment(event: Event) {
        viewModelScope.launch {
            _eventFlowComment.emit(event)
        }
    }

    private fun eventPostLike(event: Event) {
        viewModelScope.launch {
            _eventPostLike.emit(event)
        }
    }

    fun postLike(postId: String, userId: String){
        viewModelScope.launch {
            pagingRepoUseCase.postLike(postId, userId).collect{ uistate->
                eventPostLike(Event.CommentLikeUiEvnet(uistate))
            }
        }
    }

    fun postComment(content: String , normalPostId: String, userId: String){

    }

    fun getFeedList(userId: String , likedUser : String) = viewModelScope.launch {
        pagingRepoUseCase.getDailyDataPagingData(userId ,likedUser).collect { uistate ->
            eventDailyList(Event.DailyUiEvent(uistate))
        }
    }

    fun getAdoptList(userId: String) = viewModelScope.launch {
        adoptRepoUserCase.getAdoptDataPagingData(userId).collect { uistate ->
            eventAdoptList(Event.AdoptUiEvent(uistate))
        }
    }

    fun getDonationList(userId: String , donationMethod : String) = viewModelScope.launch {
        donationRepoUserCase.getDonationPagingData(userId ,donationMethod).collect { uistate ->
            eventDonationList(Event.DonationUiEvent(uistate))
        }
    }

    fun getNearShelterList(cityName: String, shelterName: String) = viewModelScope.launch {
        shelterUserCase.getShelterPagingData(cityName, shelterName).collect { uistate ->
            eventShelterList(Event.ShelterUiEvnet(uistate))
        }
    }

    fun getCommentListByPostId(postId: String) = viewModelScope.launch {
        commentPagingRepoUseCase.getCommentDataPagingData(postId).collect { uistate ->
            eventComment(Event.CommentUiEvnet(uistate))
        }
    }

    sealed class Event {
        data class DailyUiEvent(val uiState: UiState<Flow<PagingData<DailyPostFeed>>>) : Event()
        data class DonationUiEvent(val uiState: UiState<Flow<PagingData<DonationPostFeed>>>) : Event()
        data class AdoptUiEvent(val uiState: UiState<Flow<PagingData<AdoptPostFeed>>>) : Event()
        data class ShelterUiEvnet(val uiState: UiState<Flow<PagingData<ShelterEntitiyRepo>>>) : Event()
        data class CommentUiEvnet(val uiState: UiState<Flow<PagingData<Comment>>>) : Event()
        data class CommentLikeUiEvnet(val uiState: UiState<ResponseBody>) : Event()
        data class CommentPostUiEvnet(val uiState: UiState<ResponseBody>) : Event()
    }
}