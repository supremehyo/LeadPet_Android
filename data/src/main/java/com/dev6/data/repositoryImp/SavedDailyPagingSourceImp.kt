package com.dev6.data.repositoryImp
import android.util.Log
import androidx.paging.PagingState
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.SavedRemoteSource
import com.dev6.domain.model.save.SavedDailyData
import com.dev6.domain.repository.saved.SavedDailyPagingSource
import javax.inject.Inject

class SavedDailyPagingSourceImp @Inject constructor(
    userId: String,
    private val savedDailyRemoteSource: SavedRemoteSource
) : SavedDailyPagingSource() {
    var _userId = userId
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SavedDailyData> {
        return try {
            val next = params.key ?: 0
            val size = params.loadSize
            val response = savedDailyRemoteSource.getSavedDailyPost(next,size,_userId)
            Log.v("dddddd1", response.toDomain().savedDailyRequestResponse.get(0).normalPostId)
            try {
                Log.v("dddddd2", response.toDomain().savedDailyRequestResponse.get(0).normalPostId)
            } catch (e: Exception) {
                throw Exception()
            }
            if(response.last){
                LoadResult.Page(
                    data = response.toDomain().savedDailyRequestResponse,
                    prevKey = null,
                    nextKey = null
                )
            }else{
                LoadResult.Page(
                    data = response.toDomain().savedDailyRequestResponse,
                    prevKey = null,
                    nextKey = next+1
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SavedDailyData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}