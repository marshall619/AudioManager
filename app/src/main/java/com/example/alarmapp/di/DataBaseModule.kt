package com.example.alarmapp.di

import android.content.Context
import androidx.room.Room
import com.example.alarmapp.data.db.TimeDataBase
import com.example.alarmapp.util.Constants.DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context : Context
    )
    = Room.databaseBuilder(
        context.applicationContext,
        TimeDataBase::class.java,
        DATA_BASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDao(dataBase: TimeDataBase)  = dataBase.timeDao()
}