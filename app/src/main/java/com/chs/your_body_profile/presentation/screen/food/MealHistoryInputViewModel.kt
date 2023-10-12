package com.chs.your_body_profile.presentation.screen.food

import androidx.lifecycle.ViewModel
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.usecase.UpsertFoodDetailInfoUseCase
import com.chs.your_body_profile.domain.usecase.UpsertMealHistoryInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MealHistoryInputViewModel @Inject constructor(
    private val upsertMealHistoryInfoUseCase: UpsertMealHistoryInfoUseCase,
    private val upsertFoodDetailInfoUseCase: UpsertFoodDetailInfoUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MealHistoryInputState())
    val state = _state.asStateFlow()

    fun initMealHistoryInfo(mealHistoryInfo: MealHistoryInfo) {
        _state.update {
            it.copy(
                mealType = mealHistoryInfo.mealType,
                takenTime = mealHistoryInfo.takenTime,
                takenDate = mealHistoryInfo.takenDate
            )
        }
    }

    fun updateMealType(mealType: MealType) {
        _state.update {
            it.copy(
                mealType = mealType
            )
        }
    }

    fun updateTakenTime(localDateTime: LocalDateTime) {
        _state.update {
            it.copy(
                takenTime = localDateTime
            )
        }
    }

    fun removeTakenFood(foodDetailInfo: FoodDetailInfo) {

    }

    fun insertMealHistory() {

    }
}