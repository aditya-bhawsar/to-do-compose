package com.aditya.to_do.data.repositories

import com.aditya.to_do.data.ToDoDao
import com.aditya.to_do.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {

    val getAllTasks: Flow<List<ToDoTask>> = toDoDao.getAllTasks()
    val sortByLowPriority: Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTodo(taskId: Int): Flow<ToDoTask> = toDoDao.getSelectedTask(taskId = taskId)

    suspend fun addTask(toDoTask: ToDoTask) = toDoDao.insertToDo(toDoTask)
    suspend fun updateTask(toDoTask: ToDoTask) = toDoDao.updateToDo(toDoTask)
    suspend fun deleteTask(toDoTask: ToDoTask) = toDoDao.deleteTask(toDoTask)
    suspend fun deleteAllTask() = toDoDao.deleteAllTasks()

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> = toDoDao.searchDatabase(searchQuery)

}