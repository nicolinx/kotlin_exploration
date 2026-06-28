package com.example.flightsearchroomdb.data

import android.content.Context

interface AppContainer {
    val airportDao: AirportDao
}

class AppDataContainer(private val context: Context): AppContainer{
    override val airportDao: AirportDao by lazy {
        FlightDatabase.getDatabase(context).airportDao()
    }

}