package com.guagua.epoxytest

import android.app.Application
import android.content.Context
import com.airbnb.epoxy.Carousel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        Carousel.setDefaultGlobalSnapHelperFactory(null)
    }
}