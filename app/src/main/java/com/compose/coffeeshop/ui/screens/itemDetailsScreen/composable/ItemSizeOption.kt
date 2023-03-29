package com.compose.coffeeshop.ui.screens.itemDetailsScreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.compose.coffeeshop.ui.screens.itemDetailsScreen.ItemSizeOptionState
import com.compose.coffeeshop.ui.theme.ItemScreenPrice_Selection
import com.compose.coffeeshop.ui.theme.ItemScreenTextColor
import com.compose.coffeeshop.ui.theme.Rubik
import com.compose.coffeeshop.ui.theme.ItemScreenBackgroundDark

@Composable
fun ItemSizeOption(
    item: ItemSizeOptionState,
    isSelected: Boolean = true,
    onClickItem: () -> Unit
) {
         Box(
            modifier = Modifier
                .padding(vertical = 15.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isSelected) ItemScreenPrice_Selection else ItemScreenBackgroundDark.copy(alpha = 0.2f)
                )
                .border(
                    if (isSelected) (-1).dp else 1.dp,
                    ItemScreenTextColor,
                    RoundedCornerShape(16.dp)
                )
                .clickable {
                    onClickItem()
                    item.onClickItem()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.size,
                fontFamily = Rubik,
                color = if (isSelected) ItemScreenBackgroundDark else ItemScreenTextColor
            )
        }



}