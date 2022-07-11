package com.dev6.data.repositoryImple

import android.util.Log
import androidx.paging.PagingState
import com.dev6.data.entity.DailyFeedEntitiy
import com.dev6.data.remote.DailyRemoteSource
import com.dev6.data.remote.LoginRemoteSource
import com.dev6.domain.entitiyRepo.LoginEntitiy
import com.dev6.domain.entitiyRepo.UserEntity
import com.dev6.domain.repository.LoginRepository
import com.dev6.domain.repository.SamplePagingSource
import retrofit2.Response
import javax.inject.Inject

class SamplePagingSourceImp @Inject constructor(private val dailyRemoteSource: DailyRemoteSource) :
    SamplePagingSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        return try {
            val next = params.key ?: 0
            val size = params.loadSize
            val response = dailyRemoteSource.normalAllFeed(next, size)
            LoadResult.Page(
                data = response,
                prevKey = if (next == 0) null else next - 1, nextKey = next + 1
            )
        } catch (e: Exception) {
            Log.v("ssssssssssssss" , e.message.toString())
            LoadResult.Error(e)
        }


    }

    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}