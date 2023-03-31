package com.compose.coffeeshop.ui.screens.itemDetailsScreen.viewModel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.compose.coffeeshop.ui.repository.HomeScreenRepository
import com.compose.coffeeshop.ui.screens.homeScreen.viewModel.state.RecommendationDrinks
import com.compose.coffeeshop.ui.screens.navigation.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    savedStateHandel:SavedStateHandle
):ViewModel() {
    private val homeScreenRepository: HomeScreenRepository = HomeScreenRepository()

    private val args:Int = checkNotNull(savedStateHandel[DETAILS_ARGUMENT_KEY])


    private val _state = MutableStateFlow(RecommendationDrinks())
    val state = _state.asStateFlow()

    init {
        getData()
    }
    private fun getData(){
        _state.update { homeScreenRepository.getRecommendationDrinkById(args) }

    }
}