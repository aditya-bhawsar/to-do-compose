package com.aditya.to_do.di

import android.content.Context
import androidx.room.Room
import com.aditya.to_do.data.ToDoDao
import com.aditya.to_do.data.ToDoDatabase
import com.aditya.to_do.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    internal fun getDatabase(@ApplicationContext ctx: Context): ToDoDatabase = Room.databaseBuilder(
        ctx,
        ToDoDatabase::class.java,
        Constants.DB_NAME
    ).build()


    @Provides
    @Singleton
    internal fun getTodoDao(database: ToDoDatabase): ToDoDao = database.getToDoDao()
}