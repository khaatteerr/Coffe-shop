package com.compose.coffeeshop

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.compose.coffeeshop.ui.screens.navigation.BottomNavigationBar
import com.compose.coffeeshop.ui.screens.navigation.BottomNavigationItem
import com.compose.coffeeshop.ui.screens.navigation.CoffeShopNavGraph
import com.compose.coffeeshop.ui.screens.navigation.Screen
import com.compose.coffeeshop.ui.theme.CoffeeShopTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CoffeShopApp() {
    CoffeeShopTheme() {
        val navController = rememberAnimatedNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        Scaffold(backgroundColor = Color.White, bottomBar = {


            AnimatedVisibility(
                visible = backStackEntry.value?.destination?.route != Screen.ItemDetailsScreen.route,
                enter = slideInVertically(animationSpec = tween(400)) { it },
                exit = slideOutVertically(animationSpec = tween(400)) { it }
            ) {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavigationItem(
                            "Home", Screen.HomeScreen.route, ImageVector.vectorResource(id = R.drawable.ic_home),
                        ),
                        BottomNavigationItem(
                            "Favorite", Screen.FavoriteScreen.route, ImageVector.vectorResource(id = R.drawable.ic_heart),
                        ),
                        BottomNavigationItem(
                            "Cart", Screen.CartScreen.route, ImageVector.vectorResource(id = R.drawable.ic_shop),3
                        ),
                        BottomNavigationItem(
                            "Wallet", Screen.WalletScreen.route, ImageVector.vectorResource(id = R.drawable.ic_profile),
                        )
                    ), navController = navController
                ) {
                    navController.navigate(it.route){
                        launchSingleTop = true
                    }

                }
            }
        }) {
            CoffeShopNavGraph(navController)
        }


    }
}
