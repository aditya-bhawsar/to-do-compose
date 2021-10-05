package com.aditya.to_do.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aditya.to_do.data.models.ToDoTask
import com.aditya.to_do.util.Constants

@Database(
    entities = [ToDoTask::class],
    exportSchema = false,
    version = Constants.DB_VER
)
abstract class ToDoDatabase :RoomDatabase() {

    abstract fun getToDoDao(): ToDoDao

}