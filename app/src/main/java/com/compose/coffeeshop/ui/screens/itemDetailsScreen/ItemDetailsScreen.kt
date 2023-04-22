package com.compose.coffeeshop.ui.screens.itemDetailsScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.compose.coffeeshop.R
import com.compose.coffeeshop.local.roomDatabase.Drink
import com.compose.coffeeshop.local.roomDatabase.DrinkEvent
import com.compose.coffeeshop.ui.screens.detailsScreen.SizeOptionMenu
import com.compose.coffeeshop.ui.screens.detailsScreen.addAnimation
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.RecommendationDrinks
import com.compose.coffeeshop.ui.screens.itemDetailsScreen.viewModel.ItemDetailsViewModel
import com.compose.coffeeshop.ui.screens.itemDetailsScreen.viewModel.state.ItemSizeOptionState
import com.compose.coffeeshop.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ItemDetailsScreen(
    navController: NavController,
    itemDetailsViewModel: ItemDetailsViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = ItemScreenBackground
    )
    val state by itemDetailsViewModel.state.collectAsState()
    val drinkState by itemDetailsViewModel.drinkState.collectAsState()
   // val onEvent = itemDetailsViewModel::onEvent


    val drink = state.copy(
        drinkName = state.drinkName,
        drinkPrice = state.drinkPrice,
        drinkUrl = state.drinkUrl,
        rate = state.rate,
        isLiked = state.isLiked
    )
    val _drink = Drink(
        drinkName = state.drinkName,
        drinkPrice = state.drinkPrice,
        drinkUrl = state.drinkUrl,
        rate = state.rate,
        isLiked = state.isLiked,
        id = state.id
    )

    ItemDetailsContent(drink, _drink, itemDetailsViewModel)

}

@Composable
fun ItemDetailsContent(
    state: RecommendationDrinks,
    drink: Drink,
    itemDetailsViewModel: ItemDetailsViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ItemScreenBackground)
            .padding(horizontal = 30.dp)
    ) {

        var counterState by remember {
            mutableStateOf(1)
        }
        var price by remember {
            mutableStateOf(state.drinkPrice)
        }
        Log.d("ACAac", "ItemDetailsContent: $price")

        HeaderRowContent(drink, state, itemDetailsViewModel)
        Drink_Name_Price(state.copy(drinkPrice = price))
        Drink_Image_Size(state)
        AddItemsToCart(
            onAddItem = {
                counterState++
                price += state.drinkPrice
            },
            onRemoveItem = {
                if (counterState != 1) {
                    counterState--
                    price -= state.drinkPrice
                }
            },
            counterState
        )
    }
}

@Composable
fun HeaderRowContent(
    drink: Drink,

    state: RecommendationDrinks,
    itemDetailsViewModel: ItemDetailsViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(ItemScreenBackgroundDark.copy(alpha = 0.5f)),


            ) {
            val context = LocalContext.current
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowLeft),
                contentDescription = "Back to main screen",
                modifier = Modifier
                    .padding(10.dp),
                tint = Color.White
            )
        }
//        Text(
//            text = "Item Details",
//            fontFamily = Rubik,
//            fontSize = 18.sp,
//            color = ItemScreenTextColor,
//            textAlign = TextAlign.Center
//        )
        var favState by remember {
            mutableStateOf(false)
        }
        val scope = rememberCoroutineScope()

//        IconButton(onClick = {
//            scope.launch {
//                itemDetailsViewModel.removeFromFavorite(drink)
//            }
//
////                onEvent(DrinkEvent.AddDrink(drink))
////                onEvent(DrinkEvent.ShowIsLiked)
//
//
//            //state.isLiked = true
//        }) {
//            Icon(
//                Icons.Default.Favorite,
//                contentDescription = "delete contact",
//                tint = if (state.isLiked) Color.Black else Color.White
//            )
//        }
        val context = LocalContext.current

        var btnState by remember {
            mutableStateOf(state.isLiked)
        }
        IconToggleButton(
            checked = btnState, onCheckedChange = {
                btnState = !btnState

                if (btnState) {

                    Toast.makeText(context,"is added",Toast.LENGTH_LONG).show()
                    scope.launch(Dispatchers.IO) {
                        itemDetailsViewModel.addToFavorite(state)
                    }
                }
                else {

                    Toast.makeText(context,"is removed",Toast.LENGTH_LONG).show()
                    scope.launch(Dispatchers.IO) {
                        itemDetailsViewModel.removeFromFavorite(state.id)
                    }
                }

            }

        ) {
            Icon(
                tint = if (btnState) Color.Red else Color.White,
                imageVector = if (btnState) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = "deletecontact",

                )
        }
    }
}

@Composable
fun Drink_Name_Price(state: RecommendationDrinks) {
    Column(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = state.drinkName,
            fontFamily = Rubik,
            fontSize = 18.sp,
            color = ItemScreenTextColor,
            textAlign = TextAlign.Center
        )
        Row(modifier = Modifier.padding(top = 15.dp), verticalAlignment = CenterVertically) {
            Text(
                text = "$",
                fontFamily = Rubik,
                fontSize = 20.sp,
                color = ItemScreenPrice_Selection,
                textAlign = TextAlign.Center
            )
            Text(
                text = state.drinkPrice.toString(),
                fontFamily = Rubik,
                fontSize = 20.sp,
                color = ItemScreenPrice_Selection,
                textAlign = TextAlign.Center
            )
        }


    }

}

@Composable
fun Drink_Image_Size(state: RecommendationDrinks) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        var scaleImageState by remember {
            mutableStateOf(1f)
        }

        val infiniteTransition = rememberInfiniteTransition()

        val infiniteTransitionOvalCornerShape by infiniteTransition.animateFloat(
            initialValue = 220f,
            targetValue = 260f,

            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Reverse
            )
        )

        val infiniteTransitionOvalToUP by infiniteTransition.animateFloat(
            initialValue = 50f,
            targetValue = 60f,

            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Reverse
            )
        )

        val infiniteTransitionScaleImage by animateFloatAsState(
            targetValue = scaleImageState,
            animationSpec = tween(1000)

        )

        SizeOptionMenu(
            listOf(
                ItemSizeOptionState("S") { scaleImageState = 1f },
                ItemSizeOptionState("M") { scaleImageState = 1.08f },
                ItemSizeOptionState("L") { scaleImageState = 1.15f })
        ) {

        }

        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = CenterHorizontally) {

            Image(
                painter = rememberAsyncImagePainter(model = state.drinkUrl),
                contentDescription = "Drink image",
                modifier = Modifier
                    .size(200.dp)
                    .scale(infiniteTransitionScaleImage)

            )

            Canvas(modifier = Modifier, onDraw = {
                drawOval(
                    color = Color.Black.copy(alpha = 0.5f),
                    alpha = 0.5f,
                    topLeft = Offset(x = -130f, y = infiniteTransitionOvalToUP),
                    size = Size(height = 50F, width = infiniteTransitionOvalCornerShape),
                )
            })

        }

    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddItemsToCart(onAddItem: () -> Unit, onRemoveItem: () -> Unit, _state: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(50.dp, CenterHorizontally)
        ) {

            var state by remember {
                mutableStateOf(false)
            }
            val interactionSource = remember { MutableInteractionSource() }

            Box(
                Modifier
                    .size(40.dp)
                    .border(1.dp, ItemScreenTextColor, shape = CircleShape)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        state = false
                        onRemoveItem()
                    },
                contentAlignment = Alignment.TopCenter
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_minimize_24),
                    contentDescription = "minus", tint = ItemScreenTextColor
                )
            }
            AnimatedContent(targetState = _state, transitionSpec = {
                addAnimation(upOrDown = state).using(
                    SizeTransform(clip = false)
                )
            }) { targetValue ->
                Text(
                    text = targetValue.toString(),
                    fontFamily = Rubik,
                    color = ItemScreenTextColor,
                    fontSize = 18.sp
                )
            }

            Box(
                Modifier

                    .size(40.dp)
                    .border(1.dp, ItemScreenTextColor, shape = CircleShape)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        state = true
                        onAddItem()

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "plus", tint = ItemScreenTextColor
                )
            }
        }

        Card(
            modifier = Modifier
                .padding(top = 30.dp)
                .align(CenterHorizontally),
            shape = RoundedCornerShape(50.dp),
            elevation = 10.dp,
            backgroundColor = ItemScreenTextColor
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 50.dp, vertical = 15.dp),
                text = "Add to cart",
                fontFamily = Rubik,
                color = ItemScreenBackgroundDark
            )

        }
        Text(
            modifier = Modifier.padding(top = 30.dp, bottom = 20.dp),
            text = "Swipe up for product detail",
            fontFamily = Rubik,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Thin,
            color = ItemScreenTextColor, textAlign = TextAlign.Center,
            fontSize = 11.sp
        )
        var visibleState by remember {
            mutableStateOf(true)
        }

        LaunchedEffect(Unit) {
            while (true) {
                visibleState = !visibleState
                delay(3000)
            }
        }


        for (i in 1..3) {
            AnimatedVisibility(
                visibleState,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { -it } + fadeOut()
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    painter = rememberVectorPainter(Icons.Default.KeyboardArrowUp),
                    contentDescription = "Swipe up for product detail",
                    tint = when (i) {
                        2 -> ItemScreenTextColor.copy(alpha = 0.6f)
                        3 -> ItemScreenTextColor.copy(alpha = 0.4f)
                        else -> {
                            ItemScreenTextColor.copy(alpha = 1f)
                        }
                    }

                )
            }


        }
    }

}




 


