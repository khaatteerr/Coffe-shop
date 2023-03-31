package com.compose.coffeeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.compose.coffeeshop.ui.screens.detailsScreen.DetailsScreen
import com.compose.coffeeshop.ui.screens.homeScreen.HomeScreen
import com.compose.coffeeshop.ui.screens.itemDetailsScreen.ItemDetailsScreen
import com.compose.coffeeshop.ui.theme.CoffeeShopTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeShopTheme {
               HomeScreen()

               // ItemDetailsScreen()
                //  DetailsScreen()
            }
        }
    }
}

