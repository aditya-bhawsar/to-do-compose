package com.aditya.to_do.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.aditya.to_do.ui.screens.list.ListScreen
import com.aditya.to_do.ui.viewmodels.SharedViewModel
import com.aditya.to_do.util.Action
import com.aditya.to_do.util.Constants.LIST_ARGUMENT_KEY
import com.aditya.to_do.util.Constants.LIST_SCREEN
import com.aditya.to_do.util.toAction
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(
            navArgument(LIST_ARGUMENT_KEY) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->

        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        var myAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) }

        LaunchedEffect(key1 = myAction) {
            if (action != action) {
                myAction = action
                sharedViewModel.action.value = myAction
            }
        }

        val databaseAction by sharedViewModel.action

        ListScreen(
            databaseAction = databaseAction,
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
