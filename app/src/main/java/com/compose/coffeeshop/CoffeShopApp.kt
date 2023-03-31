package com.compose.coffeeshop

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.composable
import com.compose.coffeeshop.ui.screens.navigation.Screen.ItemDetailsScreen.route as route1

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
                            "Home", Screen.HomeScreen.route, Icons.Default.Home,
                        ),
                        BottomNavigationItem(
                            "Favorite", Screen.FavoriteScreen.route, Icons.Default.Favorite,
                        ),
                        BottomNavigationItem(
                            "Cart", Screen.CartScreen.route, Icons.Default.ShoppingCart,
                        ),
                        BottomNavigationItem(
                            "Wallet", Screen.WalletScreen.route, ImageVector.vectorResource(id = R.drawable.wallet_nav),
                        )
                    ), navController = navController
                ) {
                    navController.navigate(it.route)
                }
            }
        }) {
            CoffeShopNavGraph(navController)
        }


    }
}
