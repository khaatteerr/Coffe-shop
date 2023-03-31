package com.compose.coffeeshop.ui.screens.walletScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.compose.coffeeshop.ui.screens.navigation.Screen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.walletScreenRoute(navController: NavController) {
    composable(Screen.WalletScreen.route){
        walletScreenRoute(navController)
    }
}