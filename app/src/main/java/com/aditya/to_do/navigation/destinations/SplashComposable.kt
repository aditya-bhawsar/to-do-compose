package com.aditya.to_do.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.aditya.to_do.ui.screens.list.ListScreen
import com.aditya.to_do.ui.splash.SplashScreen
import com.aditya.to_do.ui.viewmodels.SharedViewModel
import com.aditya.to_do.util.Constants
import com.aditya.to_do.util.toAction

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
){
    composable(
        route = Constants.SPLASH_SCREEN
    ) {
        SplashScreen(navigateToListTaskScreen = navigateToListScreen)
    }
}