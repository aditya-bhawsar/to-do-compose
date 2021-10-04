package com.aditya.to_do.navigation

import androidx.navigation.NavHostController
import com.aditya.to_do.util.Action
import com.aditya.to_do.util.Constants.LIST_SCREEN
import com.aditya.to_do.util.Constants.TASK_SCREEN

class Screens (navController: NavHostController) {

    val lists: (Action)-> Unit = { action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){ inclusive = true}
        }
    }

    val task: (Int)-> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}