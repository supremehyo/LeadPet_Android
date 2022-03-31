package com.dev6.LeadPet
import android.app.Application
import com.dev6.core.util.LeadPetDebugTree
import dagger.hilt.android.HiltAndroidApp

import timber.log.Timber
import com.dev6.join.BuildConfig
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

//Hilt Android App 초기화
@HiltAndroidApp
class LeaPetApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(LeadPetDebugTree())
        }
        Timber.d("timber Initalized")
        var keyHash = Utility.getKeyHash(this)
        Timber.d("keyhash..$keyHash")
        KakaoSdk.init(this,getString(R.string.NATIVE_APP_KEY) )
    }

}