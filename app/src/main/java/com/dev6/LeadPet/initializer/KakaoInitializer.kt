package com.dev6.LeadPet.initializer

import android.content.Context
import androidx.startup.Initializer
import com.dev6.LeadPet.R
import com.kakao.sdk.common.KakaoSdk

class KakaoInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        KakaoSdk.init(context, context.getString(R.string.NATIVE_APP_KEY))
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}