package com.dev6.feed.viewmodel

import androidx.lifecycle.ViewModel
import com.dev6.domain.repository.daily.DailyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val repositoryDaily: DailyRepository) : ViewModel() {
   // val pagingData = repositoryDaily.getPagingData().cachedIn(viewModelScope)
}

