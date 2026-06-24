package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Query("SELECT * FROM Schedule ORDER BY arrival_time ASC")
    fun getAllSchedules(): Flow<List<BusSchedule>>

    @Query("SELECT * FROM Schedule WHERE id = :id")
    fun getSchedule(id: Int): Flow<BusSchedule>
}