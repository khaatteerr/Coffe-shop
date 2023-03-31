package com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state


data class HomePromoCardList(
    val promoCards: List<HomePromoCard> = emptyList(),
    val recommendationDrinks: List<RecommendationDrinks> = emptyList()
)

data class HomePromoCard(
    val description: String = "",
    val imageUrl: String = "",
)

data class RecommendationDrinks(
    val id: Int = 0,
    val drinkName: String = "",
    val drinkUrl: String = "",
    val drinkPrice: Double = 0.0,
    var rate: Float = 0f
)
