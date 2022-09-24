package com.dev6.core.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream


class ImageUpload() {


    var storage:FirebaseStorage = FirebaseStorage.getInstance()
    val storageRef = storage.reference

    fun uploadPhoto(fileName :String , uri: Uri , context : Context , response: (String) -> Unit){
        var temp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri)
        storage.getReference().child("image").child(fileName)
            .putBytes(compressBitmap(temp))//어디에 업로드할지 지정
            .addOnSuccessListener {
                    taskSnapshot -> // 업로드 정보를 담는다
                taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                        it-> var imageUrl=it.toString()
                    response(imageUrl)
                }
            }
            .addOnFailureListener {
                Log.v("imageuriTestError" , it.message.toString())
            }
    }

    private fun compressBitmap(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream)
        val byteArray: ByteArray = stream.toByteArray()
        return byteArray
    }
}