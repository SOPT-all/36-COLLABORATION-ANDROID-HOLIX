package org.sopt.holix

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Holix(): Application() {
    override fun onCreate() {
        super.onCreate()

        setTimber()

    }

    private fun setTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}
