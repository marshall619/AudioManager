package com.example.alarmapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alarmapp.util.Constants.TIME_TABLE_NAME

@Entity(tableName = TIME_TABLE_NAME)
data class Time(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val s_hour : Int,
    val s_min : Int,
    val e_hour : Int,
    val e_min : Int,
    val title : String,
    val day : String,
    val activeState : Boolean
)