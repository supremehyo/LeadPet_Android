package com.dev6.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dev6.core.base.UiState
import com.dev6.core.enums.FeedViewType
import com.dev6.core.enums.PostType
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.ShelterEntitiyRepo
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.comment.Comment
import com.dev6.domain.model.comment.CommentUpdate
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.DeleteSavedPost
import com.dev6.domain.model.save.Save
import com.dev6.domain.model.save.SavedPost
import com.dev6.domain.usecase.AdoptPagingRepoUseCase
import com.dev6.domain.usecase.CommentPagingRepoUseCase
import com.dev6.domain.usecase.DailyPagingRepoUseCase
import com.dev6.domain.usecase.DonationPagingRepoUseCase
import com.dev6.domain.usecase.ShelterPagingRepoUseCase
import com.dev6.domain.usecase.save.DeleteSavedPostBaseUseCase
import com.dev6.domain.usecase.save.InsertSavedPostBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
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
    private val commentPagingRepoUseCase: CommentPagingRepoUseCase,
    private val insertSavedPostBaseUseCase: @JvmSuppressWildcards InsertSavedPostBaseUseCase,
    private val deleteSavedPostBaseUseCase: @JvmSuppressWildcards DeleteSavedPostBaseUseCase
) : ViewModel() {

    private val _spinnerEntry = MutableStateFlow(emptyList<String>())
    val spinnerEntry: StateFlow<List<String>?> = _spinnerEntry
    val spinnerData = MutableStateFlow<String>("")

    val _viewNameData = MutableStateFlow<FeedViewType>(FeedViewType.HOME)
    val viewNameData: StateFlow<FeedViewType> = _viewNameData

    private val _donationPagingFlow = MutableSharedFlow<PagingData<DonationPost>>()
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

    private val _eventFlowCommentPost = MutableEventFlow<Event>()
    val eventFlowCommentPost = _eventFlowCommentPost.asEventFlow()

    private val _eventPostLike = MutableEventFlow<Event>()
    val eventPostLike = _eventPostLike.asEventFlow()

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

    private fun emitEvent(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    private fun eventCommentPost(event: Event) {
        viewModelScope.launch {
            _eventFlowCommentPost.emit(event)
        }
    }

    private fun eventPostLike(event: Event) {
        viewModelScope.launch {
            _eventPostLike.emit(event)
        }
    }

    fun postLike(postId: String, userId: String) {
        viewModelScope.launch {
            pagingRepoUseCase.postLike(postId, userId).collect { uistate ->
                eventPostLike(Event.CommentLikeUiEvent(uistate))
            }
        }
    }

    fun getFeedList(userId: String, likedUser: String) = viewModelScope.launch {
        pagingRepoUseCase.getDailyDataPagingData(userId, likedUser).cachedIn(viewModelScope).collect { uistate ->
            eventDailyList(Event.DailyUiEvent(uistate))
        }
    }

    fun getAdoptList(userId: String) = viewModelScope.launch {
        adoptRepoUserCase.getAdoptDataPagingData(userId).collect { uistate ->
            eventAdoptList(Event.AdoptUiEvent(uistate))
        }
    }

    fun getDonationList(userId: String, donationMethod: String) = viewModelScope.launch {
        donationRepoUserCase.getDonationPagingData(userId, donationMethod).collect { uistate ->
            eventDonationList(Event.DonationUiEvent(uistate))
        }
    }

    fun getNearShelterList(cityName: String, shelterName: String) = viewModelScope.launch {
        shelterUserCase.getShelterPagingData(cityName, shelterName).collect { uistate ->
            eventShelterList(Event.ShelterUiEvent(uistate))
        }
    }

    fun getCommentListByPostId(postId: String) = viewModelScope.launch {
        commentPagingRepoUseCase.getCommentDataPagingData(postId).collect { uistate ->
            eventComment(Event.CommentUiEvent(uistate))
        }
    }

    fun postCommentListByPostId(commentUpdate : CommentUpdate) = viewModelScope.launch {
        commentPagingRepoUseCase.postCommentData(commentUpdate).collect { uistate ->
            eventCommentPost(Event.CommentPostUiEvent(uistate))
        }
    }

    fun executeBookMark(postId: String, postType: PostType, userId: String) = viewModelScope.launch {
        val savePost: SavedPost = SavedPost(postId, postType, userId)
        insertSavedPostBaseUseCase(savePost).collectLatest { uiState ->
            emitEvent(Event.BookMarkUiEvent(uiState))
        }
    }

    fun executeUnBookMark(postId: String, userId: String) = viewModelScope.launch {
        val savePost: DeleteSavedPost = DeleteSavedPost(postId, userId)
        deleteSavedPostBaseUseCase(savePost).collectLatest { uiState ->
            emitEvent(Event.UnBookMarkUiEvent(uiState))
        }
    }

    sealed class Event {
        data class DailyUiEvent(val uiState: PagingData<DailyPost>) : Event()
        data class DonationUiEvent(val uiState: UiState<Flow<PagingData<DonationPost>>>) : Event()
        data class AdoptUiEvent(val uiState: UiState<Flow<PagingData<AdoptPostFeed>>>) : Event()
        data class ShelterUiEvent(val uiState: UiState<Flow<PagingData<ShelterEntitiyRepo>>>) : Event()
        data class CommentUiEvent(val uiState: UiState<Flow<PagingData<Comment>>>) : Event()
        data class CommentLikeUiEvent(val uiState: UiState<ResponseBody>) : Event()
        data class CommentPostUiEvent(val uiState: UiState<ResponseBody>) : Event()

        data class BookMarkUiEvent(val uiState: UiState<Save>) : Event()
        data class UnBookMarkUiEvent(val uiState: UiState<Save>) : Event()
    }
}
