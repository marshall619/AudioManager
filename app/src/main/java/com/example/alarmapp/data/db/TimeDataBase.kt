package com.example.alarmapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alarmapp.data.model.Time

@Database(entities = [Time :: class] , version = 1 , exportSchema = false)
abstract class TimeDataBase : RoomDatabase(){
    abstract fun timeDao() : TimeDao
}