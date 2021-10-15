package com.aditya.to_do.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.aditya.to_do.ui.screens.splash.SplashScreen
import com.aditya.to_do.util.Constants
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        exitTransition = { _, _ ->
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(300)
            )
        },
        route = Constants.SPLASH_SCREEN
    ) {
        SplashScreen(navigateToListTaskScreen = navigateToListScreen)
    }
}
