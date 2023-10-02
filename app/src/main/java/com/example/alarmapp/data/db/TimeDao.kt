package com.example.alarmapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.alarmapp.data.model.Time
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewTimeAction(time: Time)

    @Query("SELECT * FROM time_table where day = :day")
    suspend fun allTimesInDay(day : String) : List<Time>

    @Query("DELETE FROM time_table WHERE id == :id ")
    suspend fun deleteTime(id : Int)

    @Query("DELETE FROM time_table")
    suspend fun deleteAllTimes()

    @Update
    suspend fun updateTime(time: Time)

}