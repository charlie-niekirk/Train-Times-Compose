package me.cniekirk.traintimes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrainTimesApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}