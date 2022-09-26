package com.dev6.data.repositoryImp

import android.util.Log
import androidx.paging.PagingState
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.ShelterRemoteSource
import com.dev6.domain.model.ShelterEntitiyRepo
import com.dev6.domain.repository.shelter.ShelterPagingSource
import javax.inject.Inject

class ShelterPagingSourceImp @Inject constructor(
    private val dailyRemoteSource: ShelterRemoteSource,
    cityName: String,
    shelterName: String
) : ShelterPagingSource() {

    var _cityName = cityName
    var _shelterName = shelterName

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShelterEntitiyRepo> {
        return try {
            val next = params.key ?: 1
            val size = params.loadSize
            val response = dailyRemoteSource.getShelterList(_cityName, next, _shelterName, size)
            try {
                Log.v("ssssfsfsf2", response.toDomain().shelterEntitiy.get(0).shelterName)
            } catch (e: Exception) {
                throw Exception()
            }

            if(response.last){
                LoadResult.Page(
                    data = response.toDomain().shelterEntitiy,
                    prevKey = null,
                    nextKey = null
                )
            }else{
                LoadResult.Page(
                    data = response.toDomain().shelterEntitiy,
                    prevKey = null,
                    nextKey = next+1
                )
            }
        } catch (e: Exception) {
            Log.v("ssssssssssssss", e.message.toString())
            LoadResult.Error(e)
        }


    }

    override fun getRefreshKey(state: PagingState<Int, ShelterEntitiyRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}