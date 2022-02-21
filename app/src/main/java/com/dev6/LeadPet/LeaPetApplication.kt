package com.dev6.LeadPet
import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//Hilt Android App 초기화
@HiltAndroidApp
class LeaPetApplication: Application() {

    override fun onCreate() {
        super.onCreate()

    }
}