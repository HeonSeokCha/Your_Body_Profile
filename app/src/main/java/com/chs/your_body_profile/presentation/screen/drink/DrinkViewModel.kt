package com.chs.your_body_profile.presentation.screen.drink

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.usecase.GetPagingDrinkCoffeeUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingDrinkWaterUseCase
import com.chs.your_body_profile.presentation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class DrinkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPagingDrinkWaterUseCase: GetPagingDrinkWaterUseCase,
    private val getPagingDrinkCoffeeUseCase: GetPagingDrinkCoffeeUseCase,
) : ViewModel() {

    private val type: String = savedStateHandle.toRoute<Screens.DrinkList>().drinkType
    private val _state: MutableStateFlow<DrinkState> = MutableStateFlow(DrinkState())
    val state = _state
        .onStart {
            initInfo()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            DrinkState()
        )

    private fun initInfo() {
        _state.update {
            it.copy(
                pagingData = if (type == Constants.DRINK_TYPE_COFFEE) {
                    getPagingDrinkCoffeeUseCase()
                } else getPagingDrinkWaterUseCase(),
            )
        }
    }

    fun changeEvent(event: DrinkListEvent) {

    }
}