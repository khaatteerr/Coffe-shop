package com.compose.coffeeshop.ui.screens.homeScreen.composable

import android.graphics.PorterDuff
import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.RecommendationDrinks
import com.compose.coffeeshop.ui.theme.*

@Composable
fun RecommendationItem(
    state: RecommendationDrinks,
    onRecommendationClick: (RecommendationDrinks) -> Unit
) {
    Column(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .padding(start = 20.dp, end = 10.dp, top = 0.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { onRecommendationClick(state) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(HomeSectionsBaseColor),
            contentAlignment = Alignment.Center

        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = rememberAsyncImagePainter(model = state.drinkUrl),
                contentDescription = "Drink image"
            )

        }
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .align(Alignment.Start),
            text = state.drinkName,
            fontFamily = Rubik,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = HomeScreenTextColor,
            textAlign = TextAlign.Start
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = HomeScreenPriceColor
                    )
                ) {
                    append("$")
                    append(state.drinkPrice.toString())
                }

            })


            val ratingBar = RatingBar(
                LocalContext.current, null, android.R.attr.ratingBarStyleSmall
            ).apply {
                rating = state.rate
                progressDrawable.setColorFilter(
                    android.graphics.Color.parseColor("#FF5195A0"), //FFD700
                    PorterDuff.Mode.SRC_ATOP
                )
            }
            AndroidView(
                factory = { ratingBar },
                modifier = Modifier.padding(start = 5.dp)
            )
        }

    }
}