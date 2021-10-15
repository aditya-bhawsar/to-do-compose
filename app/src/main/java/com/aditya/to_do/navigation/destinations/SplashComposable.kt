package com.aditya.to_do.navigation.destinations


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aditya.to_do.ui.screens.splash.SplashScreen
import com.aditya.to_do.util.Constants

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = Constants.SPLASH_SCREEN
    ) {
        SplashScreen(navigateToListTaskScreen = navigateToListScreen)
    }
}
