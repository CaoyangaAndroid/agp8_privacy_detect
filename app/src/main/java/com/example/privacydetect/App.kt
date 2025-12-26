package com.example.privacydetect

import android.app.Application


class App : Application() {
    //private lateinit var referrerClient: InstallReferrerClient
    companion object{
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}