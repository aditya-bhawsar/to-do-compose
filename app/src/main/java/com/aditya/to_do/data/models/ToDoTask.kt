package com.aditya.to_do.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aditya.to_do.util.Constants

@Entity(tableName = Constants.TODO_TB)
data class ToDoTask (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description :String,
    var priority: Priority)