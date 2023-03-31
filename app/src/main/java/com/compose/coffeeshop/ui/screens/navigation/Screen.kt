package com.compose.coffeeshop.ui.screens.navigation


const val DETAILS_ARGUMENT_KEY = "itemId"

sealed class Screen(val route:String){
    object HomeScreen : Screen("HomeScreen")
    object ItemDetailsScreen : Screen("ItemDetailsScreen/{$DETAILS_ARGUMENT_KEY}"){
        fun passItemId(itemId:Int):String{
            return this.route.replace(oldValue = "{$DETAILS_ARGUMENT_KEY}" , newValue = itemId.toString())
        }
    }
    object FavoriteScreen:Screen("FavoriteScreen")
    object CartScreen:Screen("CartScreen")
    object WalletScreen:Screen("WalletScreen")
}
