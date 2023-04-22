package com.compose.coffeeshop.local.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DRINK_TABLE")
data class Drink(
    val drinkName: String = "",
    val drinkUrl: String = "",
    val drinkPrice: Double = 0.0,
    val rate: Float = 0f,
    var isLiked: Boolean = false ,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
