package com.dev6.domain.image

import android.graphics.Bitmap
import javax.inject.Inject

interface FirebaseStorageRepository {


    suspend fun fetchImage(uuid: String) : String
    suspend fun uploadImage( bitmap: ByteArray): String
}