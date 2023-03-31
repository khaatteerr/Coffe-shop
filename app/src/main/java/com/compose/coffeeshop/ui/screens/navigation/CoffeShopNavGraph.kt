package com.compose.coffeeshop.ui.screens.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.compose.coffeeshop.ui.screens.cartScreen.cartScreenRoute
import com.compose.coffeeshop.ui.screens.favorite.favoriteScreenRoute
import com.compose.coffeeshop.ui.screens.homeScreen.homeScreenRoute
import com.compose.coffeeshop.ui.screens.itemDetailsScreen.itemDetailsScreenRoute
import com.compose.coffeeshop.ui.screens.walletScreen.walletScreenRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CoffeShopNavGraph(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        homeScreenRoute(navController)
        itemDetailsScreenRoute(navController)
        favoriteScreenRoute(navController)
        walletScreenRoute(navController)
        cartScreenRoute(navController)
    }
}