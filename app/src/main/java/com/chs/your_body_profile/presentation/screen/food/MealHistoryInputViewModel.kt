package com.chs.your_body_profile.presentation.screen.food

import androidx.lifecycle.ViewModel
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.usecase.UpsertFoodDetailInfoUseCase
import com.chs.your_body_profile.domain.usecase.UpsertMealHistoryInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MealHistoryInputViewModel @Inject constructor(
    private val upsertMealHistoryInfoUseCase: UpsertMealHistoryInfoUseCase,
    private val upsertFoodDetailInfoUseCase: UpsertFoodDetailInfoUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MealHistoryInputState())
    val state = _state.asStateFlow()

    fun updateMealType() {

    }

    fun updateTakenTime() {

    }

    fun removeTakenFood(foodDetailInfo: FoodDetailInfo) {

    }

    fun insertMealHistory() {

    }
}