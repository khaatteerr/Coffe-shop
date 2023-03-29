package com.compose.coffeeshop.ui.screens.detailsScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.coffeeshop.R
import com.compose.coffeeshop.ui.theme.CircularBackground
import com.compose.coffeeshop.ui.theme.Rubik

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddToOrder(onAddItem:() -> Unit , onRemoveItem:()->Unit ,_state:Int  ) {



    val mainBackground = CircularBackground.copy(alpha = 0.85f)

    Row(

        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 40.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            var state by remember {
                mutableStateOf(false)
            }
            Box(
                Modifier
                    .clip(CircleShape)
                    .background(mainBackground)
                    .size(40.dp)
                    .clickable {
                        state = false
                        onRemoveItem()
                    },
                contentAlignment = Alignment.TopCenter
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_minimize_24),
                    contentDescription = "minus", tint = Color.Black.copy(alpha = 0.6f)
                )
            }
            AnimatedContent(targetState = _state, transitionSpec = {
                addAnimation(upOrDown = state).using(
                    SizeTransform(clip = false)
                )
            }) { targetValue ->
                Text(text = targetValue.toString(), fontFamily = Rubik)
            }

            Box(
                Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(mainBackground)
                    .clickable {
                        state = true
                        onAddItem()

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "plus", tint = Color.Black.copy(alpha = 0.6f)
                )
            }
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(mainBackground )
        ) {
            Text(
                "Add to order",
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 40.dp),
                fontFamily = Rubik,
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.7f)
            )
        }
    }
}
