package com.compose.coffeeshop.ui.screens.itemDetailsScreen.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.coffeeshop.local.roomDatabase.Drink
import com.compose.coffeeshop.local.roomDatabase.DrinkEvent
import com.compose.coffeeshop.local.roomDatabase.DrinkRepository
import com.compose.coffeeshop.ui.repository.HomeScreenRepository
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.RecommendationDrinks
import com.compose.coffeeshop.ui.screens.itemDetailsScreen.viewModel.state.DrinkState
import com.compose.coffeeshop.ui.screens.navigation.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    savedStateHandel: SavedStateHandle,
    private val drinkRepository: DrinkRepository
) : ViewModel() {
    private val homeScreenRepository: HomeScreenRepository = HomeScreenRepository()

    private val args: Int = checkNotNull(savedStateHandel[DETAILS_ARGUMENT_KEY])


    private val _state = MutableStateFlow(RecommendationDrinks())
    val state = _state.asStateFlow()

    private val _drinkState = MutableStateFlow(DrinkState())
    val drinkState = _drinkState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        _state.update {it.copy(
            id = homeScreenRepository.getRecommendationDrinkById(args).id,
            drinkName = homeScreenRepository.getRecommendationDrinkById(args).drinkName,
            drinkUrl = homeScreenRepository.getRecommendationDrinkById(args).drinkUrl,
            drinkPrice = homeScreenRepository.getRecommendationDrinkById(args).drinkPrice,
            rate = homeScreenRepository.getRecommendationDrinkById(args).rate,
            isLiked = homeScreenRepository.getRecommendationDrinkById(args).isLiked,
        )

        }
    }

//    fun onEvent(event: DrinkEvent) {
//        val drinkName = state.value.drinkName
//        val drinkPrice = state.value.drinkPrice
//        val drinkUrl = state.value.drinkUrl
//        val isLiked = state.value.isLiked
//        val drink = Drink(
//            drinkName = drinkName,
//            drinkUrl = drinkUrl,
//            drinkPrice = drinkPrice,
//            isLiked = isLiked
//        )
//        when (event) {
//
//            is DrinkEvent.AddDrink -> {
//
//
//                if (drinkName.isBlank() || drinkUrl.isBlank()) {
//                    return
//                }
//
//                viewModelScope.launch(Dispatchers.IO) {
//                    drinkRepository.upsertDrink(drink)
//                }
//
//            }
//            is DrinkEvent.DeleteDrink -> {
//                viewModelScope.launch {
//                    drinkRepository.deleteDrink(drink)
//                }
//            }
//            is DrinkEvent.HideIsLiked -> {
////                _state.update {
////                    it.copy(
////                        isLiked = false,
////                    )
////                }
//            }
//            is DrinkEvent.ShowIsLiked -> {
////                _state.update {
////                    it.copy(
////                        isLiked = true,
////                    )
////                }
//            }
//        }
//    }

    suspend fun addToFavorite(drink: RecommendationDrinks) {
        drinkRepository.upsertDrink(drink)
        getAllFacDrinks()
    }

    suspend fun removeFromFavorite(id: Int)   {
        drinkRepository.deleteDrink(id)
        getAllFacDrinks()
    }

    private suspend fun getAllFacDrinks() = drinkRepository.getAllFavDrinks()
}