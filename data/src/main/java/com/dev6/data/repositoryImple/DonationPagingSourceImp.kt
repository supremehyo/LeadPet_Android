package com.dev6.data.repositoryImple

import android.util.Log
import androidx.paging.PagingState
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.DonationRemoteSource
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.repository.DonationPagingSource
import javax.inject.Inject

class DonationPagingSourceImp  @Inject constructor(private val donationRemoteSource: DonationRemoteSource) :
    DonationPagingSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DonationPostFeed> {
        return try {
            val next = params.key ?: 0
            val size = params.loadSize
            val response = donationRemoteSource.donationAllFeed(next, size)
            Log.v("dddddd1" ,response.donationFeedList.get(0).donationPostId)
            try {
                Log.v("dddddd2" ,response.toDomain().donationFeedList.get(0).donationPostId)
            }catch (e : Exception){
                Log.v("dddddd3" ,e.stackTraceToString())
            }
            LoadResult.Page(
                data = response.toDomain().donationFeedList,
                prevKey = if (next == 0) null else next - 1, nextKey = next + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DonationPostFeed>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}