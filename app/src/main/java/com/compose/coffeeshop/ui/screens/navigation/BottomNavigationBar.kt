package com.compose.coffeeshop.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.compose.coffeeshop.ui.theme.ItemScreenBackground
import com.compose.coffeeshop.ui.theme.Rubik
import com.compose.coffeeshop.ui.theme.unSelectedBottomNavColor

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavigationItem) -> Unit
) {

    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(modifier = modifier, backgroundColor = Color.White, elevation = 5.dp) {

        items.forEach { item ->
            val isSelected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = isSelected,
                selectedContentColor = ItemScreenBackground,
                unselectedContentColor = unSelectedBottomNavColor,
                onClick = { onItemClick(item) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(badge = {
                                Box(
                                    modifier
                                        .size(15.dp)
                                        .clip(CircleShape)
                                        .background(ItemScreenBackground), contentAlignment = Center
                                ) {
                                    Text(
                                        text = item.badgeCount.toString(),
                                        color = Color.White,
                                        fontFamily = Rubik,
                                        fontSize = 10.sp
                                    )
                                }

                            }) {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = rememberVectorPainter(
                                        item.icon
                                    ), contentDescription = item.name
                                )

                            }
                        } else {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = rememberVectorPainter(
                                    item.icon
                                ), contentDescription = item.name
                            )
                        }

                        if (isSelected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                fontFamily = Rubik
                            )
                        }

                    }
                })
        }

    }


}