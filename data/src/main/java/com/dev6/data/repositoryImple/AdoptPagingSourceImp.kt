
package com.dev6.data.repositoryImple

import android.util.Log
import androidx.paging.PagingState
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.AdoptRemoteSource
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.domain.repository.adopt.AdoptPagingSource
import javax.inject.Inject

class AdoptPagingSourceImp  @Inject constructor(
    private val adoptRemoteSource: AdoptRemoteSource,
    userId: String) :
    AdoptPagingSource() {

    var _userId = userId

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AdoptPostFeed> {
        return try {
            val next = params.key ?: 0
            val size = params.loadSize
            val response = adoptRemoteSource.AdoptAllFeed(next, size,_userId)
            response?.adoptFeedEntitiyList?.get(0)?.adoptionPostId?.let { Log.v("ssssfsfsf1" , it) }
            try {
                Log.v("ssssfsfsf2" ,response.toDomain().adoptPostFeed.get(0).adoptionPostId)
            }catch (e : Exception){
                Log.v("ssssfsfsf3" ,e.stackTraceToString())
            }
            LoadResult.Page(
                data = response.toDomain().adoptPostFeed,
                prevKey = if (next == 0) null else next - 1, nextKey = next + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AdoptPostFeed>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}