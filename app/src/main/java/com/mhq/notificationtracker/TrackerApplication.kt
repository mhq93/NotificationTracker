package com.mhq.notificationtracker

import android.app.Application

class TrackerApplication: Application() {
    lateinit var container: DefaultAppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}