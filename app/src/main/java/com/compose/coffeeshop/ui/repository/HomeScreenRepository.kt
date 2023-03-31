package com.compose.coffeeshop.ui.repository

import com.compose.coffeeshop.ui.model.domain.FakeApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


class HomeScreenRepository {

    private val api = FakeApiServices()


    fun getAllRecommendationDrinks() = api.getAllRecommendationDrinks()

    fun getRecommendationDrinkById(id: Int) = api.getRecommendationDrinkById(id)

    fun getAllPromoCards() = api.getPromoCards()

}