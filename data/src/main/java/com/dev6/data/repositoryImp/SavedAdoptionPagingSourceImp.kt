package com.dev6.data.repositoryImp
import android.util.Log
import androidx.paging.PagingState
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.SavedRemoteSource
import com.dev6.domain.model.save.SavedAdoptionData
import com.dev6.domain.repository.saved.SavedAdoptionPagingSource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SavedAdoptionPagingSourceImp
@Inject constructor(
    userId: String,
    private val savedAdoptRemoteSource: SavedRemoteSource
) : SavedAdoptionPagingSource() {
    var _userId = userId
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SavedAdoptionData> {
        return try {
            val next = params.key ?: 1
            val size = params.loadSize
            val response = savedAdoptRemoteSource.getSavedAdoptionPost(next,size,_userId)
            try {
                Log.v("ssssfsfsf2", response.toDomain().savedAdoptionRequestResponse.get(0).adoptionPostId)
            } catch (e: IOException) {
                return LoadResult.Error(e)
            } catch (e: HttpException) {
                return LoadResult.Error(e)
            }
            if(response.last){
                LoadResult.Page(
                    data = response.toDomain().savedAdoptionRequestResponse,
                    prevKey = null,
                    nextKey = null
                )
            }else{
                LoadResult.Page(
                    data = response.toDomain().savedAdoptionRequestResponse,
                    prevKey = null,
                    nextKey = next+1
                )
            }

        } catch (e: Exception) {
            Log.v("ssssssssssssss", e.message.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SavedAdoptionData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}