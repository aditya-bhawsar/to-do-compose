package com.aditya.to_do.navigation

import androidx.navigation.NavHostController
import com.aditya.to_do.util.Action
import com.aditya.to_do.util.Constants.LIST_SCREEN
import com.aditya.to_do.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {

    val task: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

    val splash: () -> Unit = {
        navController.navigate("list/${Action.NO_ACTION}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val lists: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}
