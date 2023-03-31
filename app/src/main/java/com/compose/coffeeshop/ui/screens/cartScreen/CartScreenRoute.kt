package com.compose.coffeeshop.ui.screens.cartScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.compose.coffeeshop.ui.screens.CartScreen
import com.compose.coffeeshop.ui.screens.navigation.Screen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.cartScreenRoute(navController: NavController){
    composable(Screen.CartScreen.route){
        CartScreen()
    }
}