package com.compose.coffeeshop.ui.screens.detailsScreen

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.coffeeshop.R
import com.compose.coffeeshop.ui.theme.BOxBackground
import com.compose.coffeeshop.ui.theme.CircularBackground
import com.compose.coffeeshop.ui.theme.Rubik


@Composable
fun DetailsScreen(
) {
    val isSelected by remember {
        mutableStateOf(false)
    }

    var scaleChange by remember {
        mutableStateOf(1.05f)
    }

    var pricesState by remember {
        mutableStateOf("$30.00")
    }

    var price by remember {
        mutableStateOf(0.00)
    }
    var counterState by remember {
        mutableStateOf(1)
    }

    Column(
        Modifier
            .fillMaxSize().background(Color.White),
    ) {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(BOxBackground)
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        painter = rememberVectorPainter(Icons.Default.KeyboardArrowLeft),
                        contentDescription = "Back to home "
                    )
                }

                Text(
                    text = "Details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black.copy(alpha = 0.5f),
                    fontFamily = Rubik
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.online_shopping),
                    contentDescription = "Shop Icon"

                )

            }

            Box(
                Modifier
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {


                Card(
                    modifier = Modifier
                        .size(280.dp),
                    backgroundColor = Color.Transparent ,
                    shape = RoundedCornerShape(bottomStart = 140.dp, bottomEnd = 140.dp),

                    elevation = 0.dp
                ) {
                    Box(
                        Modifier.padding(end =20.dp, start = 20.dp, top = 60.dp).align(Alignment.BottomCenter)
                            .background(CircularBackground.copy(alpha = 0.7f), shape = CircleShape)
                             , content = {})

                    val imageWidth by animateFloatAsState(
                        if (isSelected) scaleChange else scaleChange,
                        animationSpec = tween(1000)
                    )
                    Image(
                        modifier = Modifier
                            .scale(imageWidth),
                        alignment = Alignment.TopCenter,
                        painter = painterResource(id = R.drawable.frappuccino),
                        contentDescription = "frappuccino"
                    )
                }


            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp , start = 30.dp, end = 30.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.width(120.dp),
                text = "Caramel Frappuccino", fontSize = 18.sp,
                color = Color.Black, fontFamily = Rubik
            )

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = pricesState, fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CircularBackground
                )
                Text(
                    text = "Best sales", fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray.copy(alpha = 0.7f)
                )
            }

        }

        Text(
            modifier = Modifier.padding(start = 30.dp, top = 30.dp),
            text = "Size options", fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray.copy(alpha = 0.7f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding( 20.dp  ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            SizeOptionMenu(
                listOf(
                    SizeOptionContent("Tall", "12 Fl Oz") {
                        scaleChange = if (isSelected) 1.2f else 1.1f
                        pricesState = "$30.00"
                        price = 30.0
                        counterState = 1
                    },
                    SizeOptionContent("Grande", "16 Fl Oz") {
                        scaleChange = if (isSelected) 1.3f else 1.2f
                        pricesState = "$40.00"
                        price = 40.0
                        counterState = 1
                    },
                    SizeOptionContent("Venti", "24 Fl Oz") {
                        scaleChange = if (isSelected) 1.4f else 1.3f
                        pricesState = "$50.00"
                        price = 50.0
                        counterState = 1
                    },
                    SizeOptionContent("Custom", "__ Fl Oz") {},
                )
            ) {

            }


        }


        AddToOrder(onAddItem = {
            pricesState =
                "$" + ((pricesState.removePrefix('$'.toString())
                    .toDouble() + price).toString()) + "0"
            counterState++

        }, onRemoveItem = {
            if (counterState != 1) {
                pricesState =
                    "$" + ((pricesState.removePrefix('$'.toString())
                        .toDouble() - price).toString()) + "0"
                counterState--
            }

        }, counterState)
    }


}


@ExperimentalAnimationApi
fun addAnimation(duration: Int = 500, upOrDown: Boolean): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> if (upOrDown) height else -height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> if (upOrDown) -height else height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}