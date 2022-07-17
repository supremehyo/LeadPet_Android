package com.dev6.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dev6.domain.repository.DailyPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val repositoryDaily: DailyPagingRepository) : ViewModel() {
   // val pagingData = repositoryDaily.getPagingData().cachedIn(viewModelScope)
}

