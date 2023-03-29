package com.compose.coffeeshop.ui.screens.detailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.compose.coffeeshop.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.coffeeshop.ui.theme.CircularBackground
import com.compose.coffeeshop.ui.theme.Rubik

@Composable
fun SizeOptionsItem(
    item: SizeOptionContent,
    isSelected: Boolean = true,
    modifier: Modifier = Modifier,
    onClickItem: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {

             item.onClickItem()
             onClickItem()

        }) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(
                    if (isSelected) CircularBackground.copy(alpha = 0.8f) else CircularBackground.copy(
                        alpha = 0.2f
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.coffee),
                contentDescription = null,
                tint = Color.Black.copy(alpha = 0.5f)
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            text = item.title,
            fontSize = 13.sp,
            fontFamily = Rubik,
            color = Color.Black.copy(alpha = 0.7f)
        )
        Text(
            text = item.subTitle,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray.copy(alpha = 0.9f)
        )

    }
}