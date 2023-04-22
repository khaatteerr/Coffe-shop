package com.compose.coffeeshop.ui.screens.homeScreen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.compose.coffeeshop.R
import com.compose.coffeeshop.ui.screens.homeScreen.composable.ChipsItem
import com.compose.coffeeshop.ui.screens.homeScreen.composable.RecommendationItem
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.HomeViewModel
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.ChipsItemContent
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.HomePromoCardList
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.RecommendationDrinks
import com.compose.coffeeshop.ui.screens.navigation.Screen
import com.compose.coffeeshop.ui.theme.*
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = HomeSectionsBaseColor
    )

    val state by homeViewModel.state.collectAsState()

    HomeContent(state) {
        navController.navigate(Screen.ItemDetailsScreen.passItemId(it.id))
    }
}

@ExperimentalPagerApi
@Composable
fun HomeContent(state: HomePromoCardList, onRecommendationClick: (RecommendationDrinks) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(HomeSectionsBaseColor)
    ) {
        ProfileImageSection()
        SearchField()
        ChipsSection()
        PromoCardSection(state)
        RecommendationSection(state, onRecommendationClick)

    }
}

@Composable
fun ProfileImageSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Black.copy(alpha = 0.4f)
                )
            ) {
                append("Good Morning, ")
            }
            append("Khater !")
        }, fontSize = 12.sp, fontFamily = Rubik, color = Color.Black)

        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile Image"
        )
    }
}


@Composable
private fun SearchField() {

    var textInput by remember { mutableStateOf("") }

    OutlinedTextField(
        value = textInput,
        onValueChange = {
            textInput = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(60.dp))
            .background(HomeSectionsBaseColorDark),

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Gray.copy(alpha = 0.5f),
            cursorColor = Black.copy(alpha = 0.7f),
            textColor = Black.copy(alpha = 0.7f)
        ),


        placeholder = {
            Text(
                modifier = Modifier
                    .padding(horizontal = 1.dp, vertical = 5.dp),
                text = "Cari kopi kesukaanmu",
                color = Black.copy(alpha = 0.3f),
                fontSize = 12.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.Thin,
                fontStyle = FontStyle.Italic,
            )
        },
        trailingIcon = {
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(White)

            ) {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = "searchMenu",
                    tint = ItemScreenBackground,
                    modifier = Modifier
                        .size(18.dp)
                        .align(Center)
                )
            }
        },
        shape = RoundedCornerShape(60.dp)
    )


}

@Composable
fun ChipsSection() {

    ChipsItem(
        listOf(
            ChipsItemContent("Coffee", R.drawable.coffee),
            ChipsItemContent("Cake", R.drawable.cup_cake),
            ChipsItemContent("Snack", R.drawable.snack)
        )
    )


}

@ExperimentalPagerApi
@Composable
fun PromoCardSection(state: HomePromoCardList) {

    val pagerState =
        rememberPagerState(pageCount = state.promoCards.size, initialOffscreenLimit = 2)

    LaunchedEffect(key1 = Unit, key2 = pagerState.currentPage) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(1500)
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Promo for you",
            modifier = Modifier.padding(start = 20.dp, top = 20.dp),
            fontFamily = Rubik,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier

                .height(170.dp)

        ) { page ->

            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.70f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale

                        }
                    }
                    .fillMaxWidth()
                    .padding(15.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 15.dp
            ) {
                val currentCard = state.promoCards[page]

                Box(
                    modifier = Modifier
                        .fillMaxSize()

                        .background(ItemScreenBackground)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = currentCard.imageUrl),
                        contentDescription = "Card Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = currentCard.description, modifier = Modifier
                            .width(200.dp)
                            .align(CenterStart)
                            .padding(20.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Rubik,
                        fontSize = 18.sp
                    )
                }

            }

        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(5.dp),
            activeColor = ItemScreenBackground,

            )
    }


}

@Composable
fun RecommendationSection(
    state: HomePromoCardList,
    onRecommendationClick: (RecommendationDrinks) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Recommendation",
                fontSize = 15.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold,
                color = HomeScreenTextColor
            )
            Text(
                text = "All menu",
                fontSize = 12.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.SemiBold,
                color = HomeScreenTextMenuColor
            )
        }

        LazyRow(state = rememberLazyListState()) {
            items(state.recommendationDrinks) {
                RecommendationItem(state = it, onRecommendationClick)
            }
        }


    }
}