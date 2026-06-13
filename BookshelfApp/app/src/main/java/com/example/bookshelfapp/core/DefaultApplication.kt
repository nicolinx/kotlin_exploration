package com.example.bookshelfapp.core

import android.app.Application

class DefaultApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}