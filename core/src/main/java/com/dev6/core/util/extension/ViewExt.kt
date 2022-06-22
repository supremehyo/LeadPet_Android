package com.dev6.core.util.extension

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

fun View.clicks(onUiUpdate: (() -> Unit)? = null): Flow<Unit> = callbackFlow {
    setOnClickListener {
        onUiUpdate?.invoke()
        trySend(Unit)
    }
    //닫히거나 취소될때 호출
    awaitClose { setOnClickListener(null) }

}

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L

    //반복 방출이겠네
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastEmissionTime > windowDuration) {
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}

fun View.setClickEvent(
    uiScope: CoroutineScope,
    windowDuration: Long = 1000L,
    onClick: () -> Unit,
) {
    clicks()
        .throttleFirst(windowDuration)
        .onEach { onClick.invoke() }
        .launchIn(uiScope)
}


