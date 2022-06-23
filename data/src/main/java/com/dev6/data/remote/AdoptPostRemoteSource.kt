package com.dev6.data.remote

import com.dev6.data.service.AdoptPostAPI
import com.dev6.data.service.DonatePostAPI
import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo
import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import javax.inject.Inject

interface AdoptPostRemoteSource {

    suspend fun insert(adoptPostEntityRepo: AdoptPostEntityRepo): Boolean
}

class AdoptPostRemoteSourceImpl @Inject constructor(
    private val adoptPostAPI: AdoptPostAPI
) : AdoptPostRemoteSource {
    override suspend fun insert(adoptPostEntityRepo: AdoptPostEntityRepo): Boolean =
        adoptPostAPI.addNewAdoptPost(adoptPostEntityRepo)
}

