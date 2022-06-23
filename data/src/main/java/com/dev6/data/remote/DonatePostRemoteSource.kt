package com.dev6.data.remote

import com.dev6.data.service.DonatePostAPI
import com.dev6.data.service.JoinAPI
import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import javax.inject.Inject

interface DonatePostRemoteSource {
    suspend fun insert(donatePostEntityRepo: DonatePostEntityRepo): Boolean
}

class DonatePostRemoteSourceImpl @Inject constructor(
    private val donatePostAPI: DonatePostAPI
) : DonatePostRemoteSource {

    override suspend fun insert(donatePostEntityRepo: DonatePostEntityRepo): Boolean {
        return donatePostAPI.addNewDonatePost(donatePostEntityRepo)
    }
}
