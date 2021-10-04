package com.aditya.to_do.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.aditya.to_do.navigation.destinations.listComposable
import com.aditya.to_do.navigation.destinations.taskComposable
import com.aditya.to_do.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN){
        listComposable(
            navigateToTaskScreen = screen.task
        )
        taskComposable(
            navigateToListScreen = screen.lists
        )
    }
}