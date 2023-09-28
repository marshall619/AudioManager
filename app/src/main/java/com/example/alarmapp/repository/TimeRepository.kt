package com.example.alarmapp.repository

import com.example.alarmapp.data.db.TimeDao
import com.example.alarmapp.data.model.Time
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TimeRepository @Inject constructor(
    private val dao : TimeDao
){

    suspend fun allTimesInDay(day : String) : List<Time>  = dao.allTimesInDay(day)

    suspend fun insertNewTimeAction(time: Time) = dao.insertNewTimeAction(time)

    suspend fun deleteTime(id : Int) = dao.deleteTime(id)

    suspend fun deleteAllTimes() = dao.deleteAllTimes()

}