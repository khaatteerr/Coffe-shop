package com.compose.coffeeshop.ui.screens.homeScreen

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.compose.coffeeshop.ui.screens.navigation.Screen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeScreenRoute(navController: NavController) {
    composable(
        Screen.HomeScreen.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -300 },
                animationSpec = tween(
                    300,
                    easing = FastOutSlowInEasing
                )
            )+ fadeOut(animationSpec = tween(200))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = {-300},
                animationSpec = tween(
                    300,
                    easing = FastOutSlowInEasing
                )
            )+fadeIn(animationSpec = tween(200)) }
    ) { HomeScreen(navController) }
}