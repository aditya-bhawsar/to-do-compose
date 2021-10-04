package com.aditya.to_do.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.to_do.data.models.ToDoTask
import com.aditya.to_do.data.repositories.ToDoRepository
import com.aditya.to_do.util.RequestState
import com.aditya.to_do.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class SharedViewModel @Inject constructor(private val toDoRepo: ToDoRepository): ViewModel() {

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    val searchTextState: MutableState<String> =
        mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> get() = _allTasks

    fun getAllTasks() = viewModelScope.launch {
        _allTasks.value = RequestState.Loading
        try {
            toDoRepo.getAllTasks.collect {
                _allTasks.value = RequestState.Success(it)
            }
        }catch (e: Throwable){
            _allTasks.value = RequestState.Error(e)
        }
    }

}