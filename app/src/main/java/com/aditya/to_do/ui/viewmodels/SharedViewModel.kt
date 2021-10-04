package com.aditya.to_do.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.to_do.data.models.ToDoTask
import com.aditya.to_do.data.repositories.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val toDoRepo: ToDoRepository): ViewModel() {

    private val _allTasks = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks: StateFlow<List<ToDoTask>> get() = _allTasks

    fun getAllTasks() = viewModelScope.launch {
        toDoRepo.getAllTasks.collect {
            _allTasks.value = it
        }
    }

}