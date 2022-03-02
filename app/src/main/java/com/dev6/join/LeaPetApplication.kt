package com.dev6.join
import android.app.Application
import com.dev6.core.util.LeadPetDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import com.dev6.join.BuildConfig

//Hilt Android App 초기화
@HiltAndroidApp
class LeaPetApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(LeadPetDebugTree())
        }
    }
}