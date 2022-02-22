package com.dev6.join
import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//Hilt Android App 초기화
@HiltAndroidApp
class LeaPetApplication: Application() {

    override fun onCreate() {
        super.onCreate()

    }
}