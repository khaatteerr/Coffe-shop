package com.compose.coffeeshop.ui.screens.homeScreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.ChipsItemContent
import com.compose.coffeeshop.ui.theme.HomeSectionsBaseColor
import com.compose.coffeeshop.ui.theme.HomeSectionsBaseColorDark
import com.compose.coffeeshop.ui.theme.ItemScreenBackground
import com.compose.coffeeshop.ui.theme.Rubik

@Composable
fun ChipsItem(
    chipContent: List<ChipsItemContent>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chipContent.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(120.dp)
                    .padding(vertical = 20.dp, horizontal = 10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(if (selectedChipIndex == it) ItemScreenBackground else HomeSectionsBaseColorDark)
                    .border(
                        1.dp,
                        if (selectedChipIndex == it) Color.Transparent else Color.Black.copy(alpha = 0.1f),
                        RoundedCornerShape(50.dp)
                    )
                    .clickable { selectedChipIndex = it }

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.White)

                    ) {
                        Icon(
                            painter = painterResource(chipContent[it].icon),
                            contentDescription = "searchMenu",
                            tint = ItemScreenBackground,
                            modifier = Modifier
                                .size(18.dp)
                                .align(Alignment.Center)
                        )
                    }

                    Text(
                        modifier = Modifier.padding(end = 5.dp),
                        text = chipContent[it].category,
                        fontSize = 10.sp,
                        fontFamily = Rubik,
                        fontWeight = FontWeight.Thin,
                        color = if (selectedChipIndex == it) Color.White else Color.Black.copy(alpha = 0.5f)
                    )

                }
            }
        }
    }
}