package com.compose.coffeeshop.ui.screens.navigation

import android.graphics.drawable.VectorDrawable
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)
