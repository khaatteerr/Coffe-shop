package com.compose.coffeeshop.ui.screens.itemDetailsScreen.viewModel.state

import com.compose.coffeeshop.local.roomDatabase.Drink

data class DrinkState(
    val drinks:List<Drink> = emptyList(),
    val drinkName: String = "",
    val drinkUrl: String = "",
    val drinkPrice: Double = 0.0,
    val isLiked :Boolean = false,
    var rate: Float = 0f,
)
