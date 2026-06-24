package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {
    fun getAllSchedules(): Flow<List<BusSchedule>>

    fun getAllSchedulesByStop(stopName: String): Flow<List<BusSchedule>>

    fun getSchedule(id: Int): Flow<BusSchedule>
}

class BusScheduleRepositoryImpl(private val busScheduleDao: BusScheduleDao) :
    BusScheduleRepository {
    override fun getAllSchedules(): Flow<List<BusSchedule>> {
        return busScheduleDao.getAllSchedules()
    }

    override fun getAllSchedulesByStop(stopName: String): Flow<List<BusSchedule>> {
        return busScheduleDao.getAllSchedulesByStop(stopName)
    }

    override fun getSchedule(id: Int): Flow<BusSchedule> {
        return busScheduleDao.getScheduleById(id)
    }
}