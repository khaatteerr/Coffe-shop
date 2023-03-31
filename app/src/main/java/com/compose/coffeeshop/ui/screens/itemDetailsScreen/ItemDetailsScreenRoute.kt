package com.compose.coffeeshop.ui.screens.itemDetailsScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.navigation.*
import com.compose.coffeeshop.ui.screens.navigation.DETAILS_ARGUMENT_KEY
import com.compose.coffeeshop.ui.screens.navigation.Screen
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("ComposableDestinationInComposeScope")
fun NavGraphBuilder.itemDetailsScreenRoute(navController: NavController) {

    composable(
        route = Screen.ItemDetailsScreen.route,

        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(100))
        },
        arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    )
    {
        Log.d("ARG", it.arguments?.getInt(DETAILS_ARGUMENT_KEY).toString())
        ItemDetailsScreen(navController)
    }

}


