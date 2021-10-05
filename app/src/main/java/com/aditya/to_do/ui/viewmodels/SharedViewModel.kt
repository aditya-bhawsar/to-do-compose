package com.aditya.to_do.ui.viewmodels

import android.app.DownloadManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.to_do.data.models.Priority
import com.aditya.to_do.data.models.ToDoTask
import com.aditya.to_do.data.repositories.ToDoRepository
import com.aditya.to_do.util.Action
import com.aditya.to_do.util.Constants.TITLE_LENGTH_LIMIT
import com.aditya.to_do.util.RequestState
import com.aditya.to_do.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class SharedViewModel @Inject constructor(private val toDoRepo: ToDoRepository): ViewModel() {

    val action : MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    var id: MutableState<Int> = mutableStateOf(0)
    var title: MutableState<String> = mutableStateOf("")
    var desc: MutableState<String> = mutableStateOf("")
    var priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    var searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    var searchTextState: MutableState<String> =
        mutableStateOf("")

    private var _searchTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val searchTasks: StateFlow<RequestState<List<ToDoTask>>> get() = _searchTasks

    fun getSearchedTasks(searchQuery: String) = viewModelScope.launch {
        _searchTasks.value = RequestState.Loading
        try {
            toDoRepo.searchDatabase(searchQuery = "%$searchQuery%").collect {
                _searchTasks.value = RequestState.Success(it)
            }
        }catch (e: Throwable){
            _searchTasks.value = RequestState.Error(e)
        }
        searchAppBarState.value = SearchAppBarState.TRIGGERED
    }

    private var _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> get() = _allTasks

    fun getAllTasks() = viewModelScope.launch {
        println("sHITTED")
        _allTasks.value = RequestState.Loading
        try {
            toDoRepo.getAllTasks.collect {
                _allTasks.value = RequestState.Success(it)
            }
        }catch (e: Throwable){
            _allTasks.value = RequestState.Error(e)
        }
    }

    private var _selectedTask : MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> get() = _selectedTask

    fun getSelectedTask(taskId: Int)= viewModelScope.launch {
        toDoRepo.getSelectedTodo(taskId).collect { task ->
            _selectedTask.value = task
        }
    }

    fun updateSelectedTask(selectedTask : ToDoTask?){
        if(selectedTask != null){
            id.value = selectedTask.id
            title.value = selectedTask.title
            priority.value = selectedTask.priority
            desc.value = selectedTask.description
        }else{
            id.value = 0
            title.value = ""
            priority.value = Priority.LOW
            desc.value = ""
        }
    }

    fun updateTitle(updatedTitle: String){
        if(updatedTitle.length < TITLE_LENGTH_LIMIT){
            title.value = updatedTitle
        }
    }

    private fun addTask() = viewModelScope.launch(Dispatchers.IO) {
        val todoTask  = ToDoTask(
            id = 0,
            title = title.value,
            description = desc.value,
            priority = priority.value
        )
        toDoRepo.addTask(todoTask)

        searchAppBarState.value = SearchAppBarState.CLOSED
    }

    fun handleDatabaseAction(action: Action){
        when(action){
            Action.ADD -> { addTask() }
            Action.UPDATE -> { updateTask() }
            Action.DELETE -> { deleteTask() }
            Action.UNDO-> { addTask() }
            Action.DELETE_ALL -> { deleteAllTasks() }
            else -> {}
        }
        this.action.value = Action.NO_ACTION
    }

    private fun updateTask() = viewModelScope.launch(Dispatchers.IO) {
        val toDoTask = ToDoTask(
            id = id.value,
            title = title.value,
            description = desc.value,
            priority = priority.value
        )
        toDoRepo.updateTask(toDoTask)
    }

    private fun deleteTask()= viewModelScope.launch(Dispatchers.IO){
        val toDoTask = ToDoTask(
            id = id.value,
            title = title.value,
            description = desc.value,
            priority = priority.value
        )
        toDoRepo.deleteTask(toDoTask)
    }

    private fun deleteAllTasks()= viewModelScope.launch(Dispatchers.IO){
        toDoRepo.deleteAllTask()
    }

    fun validateFields(): Boolean{
        return title.value.isNotEmpty() && desc.value.isNotEmpty()
    }
}