package com.aditya.to_do.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.aditya.to_do.ui.screens.task.TaskScreen
import com.aditya.to_do.ui.viewmodels.SharedViewModel
import com.aditya.to_do.util.Action
import com.aditya.to_do.util.Constants.TASK_ARGUMENT_KEY
import com.aditya.to_do.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(
            navArgument(TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val taskId: Int = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) sharedViewModel.updateSelectedTask(
                selectedTask
            )
        }

        TaskScreen(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen,
            selectedTask = selectedTask
        )
    }
}