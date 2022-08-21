package com.dev6.post.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dev6.core.enums.PostType
import com.dev6.core.util.EventFlow
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _postTypeEventFlow = MutableEventFlow<PostType>()
    val postTypeEventFlow = savedStateHandle.getLiveData<PostType>("postType")

//    init {

//    }
}