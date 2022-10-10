package com.dev6.data.repositoryImp

import android.util.Log
import androidx.paging.PagingState
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.DonationRemoteSource
import com.dev6.data.remote.SavedRemoteSource
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.SavedDonationData
import com.dev6.domain.repository.donate.DonationPagingSource
import com.dev6.domain.repository.saved.SavedDonationPagingSource
import javax.inject.Inject

class SavedDonationPagingSourceImp @Inject constructor(
    userId: String,
    private val savedDonationRemoteSource: SavedRemoteSource
) : SavedDonationPagingSource() {
    var _userId = userId
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SavedDonationData> {
        return try {
            val next = params.key ?: 0
            val size = params.loadSize
            val response = savedDonationRemoteSource.getSavedDonationPost(next,size,_userId)
            Log.v("dddddd1", response.toDomain().savedDonationRequestResponse.get(0).donationPostId)
            try {
                Log.v("dddddd2", response.toDomain().savedDonationRequestResponse.get(0).donationPostId)
            } catch (e: Exception) {
                throw Exception()
            }
            if(response.last){
                LoadResult.Page(
                    data = response.toDomain().savedDonationRequestResponse,
                    prevKey = null,
                    nextKey = null
                )
            }else{
                LoadResult.Page(
                    data = response.toDomain().savedDonationRequestResponse,
                    prevKey = null,
                    nextKey = next+1
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SavedDonationData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
