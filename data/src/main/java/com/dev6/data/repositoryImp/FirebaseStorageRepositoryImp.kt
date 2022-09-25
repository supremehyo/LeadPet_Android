package com.dev6.data.repositoryImp

import android.graphics.Bitmap
import android.util.Log
import com.dev6.data.remote.DonationRemoteSource
import com.dev6.domain.image.FirebaseStorageRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.ByteArrayOutputStream
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseStorageRepositoryImp @Inject constructor(private val storage: FirebaseStorage) :
    FirebaseStorageRepository {

    override suspend fun uploadImage(byteArray: ByteArray) =
        suspendCancellableCoroutine<String> { continuation ->
            val uuid = UUID.randomUUID().toString()
            storage.reference.child("image").child(uuid)
                .putBytes(byteArray)
                .addOnSuccessListener {
                    continuation.resume(uuid)
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }

    override suspend fun fetchImage() {
        TODO("Not yet implemented")
    }

}