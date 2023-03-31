package com.compose.coffeeshop.ui.screens.homeScreen.viewModel

import androidx.lifecycle.ViewModel
import com.compose.coffeeshop.ui.repository.HomeScreenRepository
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.HomePromoCard
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.HomePromoCardList
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.RecommendationDrinks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val homeScreenRepository: HomeScreenRepository = HomeScreenRepository()


    private val _state = MutableStateFlow(HomePromoCardList())
    val state = _state.asStateFlow()

    init {
        getPromoCards()
        getRecommendationDrinks()
    }

    private fun getPromoCards() {
        _state.update {
            it.copy(
                promoCards =homeScreenRepository.getAllPromoCards()
            )
        }
    }

    private fun getRecommendationDrinks() {
        _state.update {
            it.copy(
                recommendationDrinks = homeScreenRepository.getAllRecommendationDrinks()
            )
        }
    }

}