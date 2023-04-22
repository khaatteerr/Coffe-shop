package com.compose.coffeeshop.ui.screens.cartScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.coffeeshop.ui.theme.HomeSectionsBaseColor
import com.compose.coffeeshop.ui.theme.ItemScreenBackground

@Composable
fun CartScreen() {
    Box(modifier = Modifier.fillMaxSize().background(HomeSectionsBaseColor))
}