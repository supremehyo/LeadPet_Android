package com.dev6.data.repositoryImp

import android.util.Log
import androidx.paging.PagingState
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.DailyRemoteSource
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.repository.daily.DailyPagingSource
import javax.inject.Inject

class DailyPagingSourceImp @Inject constructor(
    private val userId: String,
    private val likedUser: String,
    private val dailyRemoteSource: DailyRemoteSource
) : DailyPagingSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DailyPost> {
        return try {
            val next = params.key ?: 0
            val size = params.loadSize
            val response = dailyRemoteSource.dailyAllFeed(next, size, userId, likedUser)
            try {
                Log.v("ssssfsfsf2", response.toDomain().dailyFeedEntitiy.get(0).normalPostId)
            } catch (e: Exception) {
                throw Exception()
            }
            LoadResult.Page(
                data = response.toDomain().dailyFeedEntitiy,
                prevKey = if (next == 0) null else next - 1,
                nextKey = next + 1
            )
        } catch (e: Exception) {
            Log.v("ssssssssssssss", e.message.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DailyPost>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
