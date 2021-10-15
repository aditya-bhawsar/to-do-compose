package com.aditya.to_do.ui.screens.list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.aditya.to_do.R
import com.aditya.to_do.ui.theme.fabBackgroundColor
import com.aditya.to_do.ui.viewmodels.SharedViewModel
import com.aditya.to_do.util.Action
import com.aditya.to_do.util.SearchAppBarState
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListScreen(
    databaseAction: Action,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = databaseAction) {
        sharedViewModel.handleDatabaseAction(action = databaseAction)
    }

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        onComplete = { sharedViewModel.action.value = it },
        onUndoClicked = { sharedViewModel.action.value = it },
        title = sharedViewModel.title.value,
        action = databaseAction
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                allTasks = allTasks,
                searchTasks = searchedTasks,
                lowPriorityTasks = lowPriorityTasks,
                highPriorityTasks = highPriorityTasks,
                sortState = sortState,
                searchAppBarState = searchAppBarState,
                onSwipeToDelete = { action, toDoTask ->
                    sharedViewModel.action.value = action
                    sharedViewModel.updateSelectedTask(selectedTask = toDoTask)
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                },
                navigateToTaskScreen = navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(navigateToTaskScreen: (taskId: Int) -> Unit) {
    FloatingActionButton(
        onClick = {
            navigateToTaskScreen(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    onComplete: (Action) -> Unit,
    onUndoClicked: (Action) -> Unit,
    title: String,
    action: Action
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(action = action, title = title),
                    actionLabel = setActionLabel(action = action)
                )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
            onComplete(Action.NO_ACTION)
        }
    }
}

private fun setMessage(action: Action, title: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All tasks Removed"
        else -> "${action.name}: $title"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}
