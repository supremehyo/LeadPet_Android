package com.dev6.core.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.ContentProviderCompat.requireContext
import java.io.ByteArrayOutputStream


object ImageUpload {


    fun convertUrlToBitmap(uri: Uri, context: Context) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    context.contentResolver,
                    uri
                )
            )
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }

    fun compressBitmap(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream)
        val byteArray: ByteArray = stream.toByteArray()
        return byteArray
    }
}