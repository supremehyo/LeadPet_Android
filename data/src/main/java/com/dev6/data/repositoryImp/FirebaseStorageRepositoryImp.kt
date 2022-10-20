package com.dev6.data.repositoryImp

import com.dev6.domain.image.FirebaseStorageRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.suspendCancellableCoroutine
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

    override suspend fun fetchImage(uuid: String): String =
        suspendCancellableCoroutine<String> { continuation ->
            var temp = storage.reference.child("image").child(uuid)
            temp.downloadUrl
                .addOnSuccessListener { p0 -> continuation.resume(p0.toString()) }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
}
