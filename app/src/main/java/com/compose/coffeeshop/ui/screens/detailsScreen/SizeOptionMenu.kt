package com.compose.coffeeshop.ui.screens.detailsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.coffeeshop.ui.screens.itemDetailsScreen.ItemSizeOptionState
import com.compose.coffeeshop.ui.screens.itemDetailsScreen.composable.ItemSizeOption

@Composable
fun <T> SizeOptionMenu(
    items: List<T>,
    initialSelectedItemIndex: Int = 0,
    onClickItem: (T) -> Unit
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

// this is Row for first screen i change it to Column to change design
    Column(
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier , horizontalAlignment = Alignment.Start

    ) {

        items.forEachIndexed { index, item ->
            when (item) {
                is SizeOptionContent -> {
                    SizeOptionsItem(
                        item = item,
                        isSelected = index == selectedItemIndex,
                    ) {
                        selectedItemIndex = index
                        onClickItem(item)

                    }
                }

                is ItemSizeOptionState -> {
                    ItemSizeOption(item = item, isSelected = index == selectedItemIndex) {
                        selectedItemIndex = index
                        onClickItem(item)
                    }
                }

                else -> {}
            }


        }
    }
}

