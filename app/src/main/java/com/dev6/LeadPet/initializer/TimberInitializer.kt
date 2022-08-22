package com.dev6.LeadPet.initializer

import android.content.Context
import androidx.startup.Initializer
import com.dev6.core.util.LeadPetDebugTree
import com.dev6.join.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(LeadPetDebugTree())
            Timber.d("timber Initalized")
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
    return  mutableListOf()
    }
}