package com.compose.coffeeshop.local.roomDatabase

sealed interface DrinkEvent{
    data class AddDrink(val drink: Drink): DrinkEvent
    data class DeleteDrink(val drink: Drink) : DrinkEvent
    object ShowIsLiked:DrinkEvent
    object HideIsLiked:DrinkEvent
}