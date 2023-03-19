package com.compose.coffeeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.coffeeshop.ui.theme.BOxBackground
import com.compose.coffeeshop.ui.theme.CircularBackground
import com.compose.coffeeshop.ui.theme.CoffeeShopTheme
import com.compose.coffeeshop.ui.theme.Rubik
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeShopTheme {

                ShowImage()
            }
        }
    }
}

@Composable
fun ShowImage(
) {
    val isSelected by remember {
        mutableStateOf(false)
    }

    var scaleChange by remember {
        mutableStateOf(0.9f)
    }


    Column(
        Modifier
            .fillMaxSize(),
    ) {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                verticalAlignment = CenterVertically,
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

                    .fillMaxWidth(), contentAlignment = Center
            ) {


                Box(
                    Modifier
                        .background(CircularBackground.copy(alpha = 0.5f), shape = CircleShape)
                        .size(250.dp)

                        .fillMaxHeight(),
                    contentAlignment = Center

                ) {

                }

                val imageWidth by animateFloatAsState(
                    if (isSelected) scaleChange else scaleChange,
                    animationSpec = tween(1000)
                )

                Image(
                    modifier = Modifier
                        .scale(imageWidth),
                    alignment = TopCenter,
                    painter = painterResource(id = R.drawable.frappuccino),
                    contentDescription = "frappuccino"
                )

            }
        }

        var pricesState by remember {
            mutableStateOf("$30.00")
        }

        var price by remember {
            mutableStateOf(0.0)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),

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
            modifier = Modifier.padding(start = 30.dp),
            text = "Size options", fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray.copy(alpha = 0.7f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SizeOptionMenu(
                listOf(
                    SizeOptionContent("Tall", "12 Fl Oz") {
                        scaleChange = if (isSelected) 1f else 0.9f
                        pricesState = "$30.00"
                        price = 30.0
                    },
                    SizeOptionContent("Grande", "16 Fl Oz") {
                        scaleChange = if (isSelected) 1.1f else 1f
                        pricesState = "$40.00"
                        price = 40.0
                    },
                    SizeOptionContent("Venti", "24 Fl Oz") {
                        scaleChange = if (isSelected) 1.5f else 1.1f
                        pricesState = "$50.00"
                        price = 50.0
                    },
                    SizeOptionContent("Custom", "__ Fl Oz") {},
                )
            ) {
                // if (it.title=="Tall")

            }


        }


        AddToOrder(onAddItem = {
            pricesState  =    "$"+((pricesState.removePrefix('$'.toString()).toDouble() +price).toString())
        }, onRemoveItem = {
             pricesState  = "$"+((pricesState.removePrefix('$'.toString()).toDouble() - price).toString())
        } ,price )
    }


}

@Composable
fun SizeOptionMenu(
    items: List<SizeOptionContent>,
    initialSelectedItemIndex: Int = 0,
    onClickItem: (SizeOptionContent) -> Unit
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        items.forEachIndexed { index, item ->
            SizeOptionsItem(
                item = item,
                isSelected = index == selectedItemIndex,

                ) {
                selectedItemIndex = index
                onClickItem(item)
            }
        }
    }

}

@Composable
fun SizeOptionsItem(
    item: SizeOptionContent,
    isSelected: Boolean = true,
    onClickItem: () -> Unit
) {

    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {


            if (!isSelected) {
                item.onClickItem()
            }
            onClickItem()

        }) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(
                    if (isSelected) CircularBackground.copy(alpha = 0.6f) else CircularBackground.copy(
                        alpha = 0.2f
                    )
                ),
            contentAlignment = Center
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddToOrder(onAddItem:() -> Unit , onRemoveItem:()->Unit ,prices:Double ) {

    var counterState by remember {
        mutableStateOf(1)
    }

    val mainBackground = CircularBackground.copy(alpha = 0.6f)

    Row(

        verticalAlignment = CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 40.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = CenterVertically,
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
                        if (counterState != 1) counterState--
                        state = false
                        onRemoveItem()
                    },
                contentAlignment = TopCenter
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_minimize_24),
                    contentDescription = "minus", tint = Color.Black.copy(alpha = 0.6f)
                )
            }
            AnimatedContent(targetState = counterState, transitionSpec = {
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
                    .background(mainBackground.copy(alpha = 0.6f))
                    .clickable {
                        counterState++
                        state = true
                        onAddItem( )

                    },
                contentAlignment = Center
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
                .background(mainBackground.copy(alpha = 0.6f))
        ) {
            Text(
                "Add to order",
                modifier = Modifier.padding(18.dp),
                fontFamily = Rubik,
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.7f)
            )
        }
    }
}

data class SizeOptionContent(
    val title: String,
    val subTitle: String,
    val onClickItem: () -> Unit,
)

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 500, upOrDown: Boolean): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> if (upOrDown) height else -height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> if (upOrDown) -height else height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )

}