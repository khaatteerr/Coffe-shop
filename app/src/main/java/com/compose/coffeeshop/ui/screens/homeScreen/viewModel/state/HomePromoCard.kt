package com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state

import androidx.room.Entity
import androidx.room.PrimaryKey


data class HomePromoCardList(
    val promoCards: List<HomePromoCard> = emptyList(),
    val recommendationDrinks: List<RecommendationDrinks> = emptyList()
)

data class HomePromoCard(
    val description: String = "",
    val imageUrl: String = "",
)



@Entity(tableName = "favorite_drinks_db")
data class RecommendationDrinks(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val drinkName: String = "",
    val drinkUrl: String = "",
    val drinkPrice: Double = 0.0,
    var rate: Float = 0f,
    var isLiked: Boolean = false
)
