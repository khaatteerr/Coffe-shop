package com.compose.coffeeshop.ui.model.domain

import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.HomePromoCard
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.RecommendationDrinks

class FakeApiServices {

    private val recommendationDrinks = listOf(
            RecommendationDrinks(0,"Americano Coffe","https://i.ibb.co/HrhrZrY/imgbin-coffee-tea-latte-espresso-starbucks-png.png",15.0,4.5f),
            RecommendationDrinks(1,"Cappuccino","https://i.ibb.co/7WTRnPw/favpng-coffee-starbucks-cappuccino-tea-espresso.png",20.0,4.0f),
            RecommendationDrinks(2,"Latte","https://i.ibb.co/Dt52j3f/pngegg.png",25.0,4.5f),
            RecommendationDrinks(3,"Espresso","https://i.ibb.co/XSGJhJL/pngegg-1.png",15.0,3.5f),
            RecommendationDrinks(4,"Mocha","https://i.ibb.co/GFZMFg1/pngegg-2.png",30.0,4.8f),
            RecommendationDrinks(5,"Chai Tea Latte","https://i.ibb.co/sRk7CW2/pngegg-3.png",25.0,4.2f),
            RecommendationDrinks(6,"Iced Tea","https://i.ibb.co/Q95Rqrm/pngwing-com.png",10.0,3.0f)


    )

    private val promoCards = listOf(
        HomePromoCard(
            "50 % discount for All Purchases this morning",
            "https://cdn11.bigcommerce.com/s-nbc124/images/stencil/original/carousel/59/slider1.jpg?c=2"
        ),
        HomePromoCard(
            " Don't miss out on the chance to grab your favorite Starbucks drinks",
            "https://cdn.shopify.com/s/files/1/0020/3859/5683/files/hero-img_ccf46515-65de-4a96-9130-b834dca431c9.jpg?v=1631294738"
        ),
        HomePromoCard(
            "Head over to your nearest Starbucks now and take advantage of this amazing offer",
            "https://cdn.shopify.com/s/files/1/1914/8475/files/lth-craft-beautiful-coffee-new-with-overlay_1600x.jpg?v=1614382271"
        ),
        HomePromoCard(
            "Limited time offer: 30% discount on everything!",
            "https://suntrustblog.com/wp-content/uploads/2021/11/co0.jpg"
        ),
        HomePromoCard(
            "Happy Hour: 50% off all Frappuccinos",
            "https://www.foodbusinessnews.net/ext/resources/2023/02/06/Starbucks_Lead-AdSt-Dontree.jpg?height=667&t=1675689655&width=1080"
        ),
    )

    fun getAllRecommendationDrinks():List<RecommendationDrinks> = recommendationDrinks

    fun getRecommendationDrinkById(id:Int):RecommendationDrinks{
        return recommendationDrinks[id]
    }

    fun getPromoCards() = promoCards
}