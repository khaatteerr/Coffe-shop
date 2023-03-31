package com.compose.coffeeshop.ui.screens.favorite

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.compose.coffeeshop.ui.screens.navigation.Screen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.favoriteScreenRoute(navController: NavController){
    composable(route = Screen.FavoriteScreen.route){
        FavoriteScreen()
    }
}