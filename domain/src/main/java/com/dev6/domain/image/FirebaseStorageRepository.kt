package com.dev6.domain.image

import javax.inject.Inject

interface FirebaseStorageRepository {

    suspend fun uploadImage()

    suspend fun fetchImage()
}