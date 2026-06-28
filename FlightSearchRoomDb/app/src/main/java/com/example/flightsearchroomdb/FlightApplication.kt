package com.example.flightsearchroomdb

import android.app.Application
import com.example.flightsearchroomdb.data.AppContainer
import com.example.flightsearchroomdb.data.AppDataContainer

class FlightApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}