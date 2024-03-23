package com.chs.your_body_profile.presentation.screen.food

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.usecase.GetDayMealTypeListUseCase
import com.chs.your_body_profile.domain.usecase.UpsertMealHistoryInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MealHistoryInputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val upsertMealHistoryInfoUseCase: UpsertMealHistoryInfoUseCase,
    private val getDayMealTypeListUseCase: GetDayMealTypeListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MealHistoryInputState())
    val state = _state.asStateFlow()

    private val takenDate: LocalDate = (savedStateHandle[Constants.ARG_TAKEN_DATE] ?: 0L).toLocalDate()
    private val takenMealType: MealType = MealType.entries.find { mealType ->
        mealType.mean.second == savedStateHandle[Constants.ARG_TAKEN_MEAL_TYPE]
    } ?: MealType.MORNING

    fun initMealHistoryInfo(
        mealHistoryInfo: MealHistoryInfo
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    takenDateTime = mealHistoryInfo.takenDateTime,
                    takenFoodList = mealHistoryInfo.foodList,
                )
            }
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
                takenDateTime = localDateTime
            )
        }
    }

    fun removeTakenFood(foodDetailInfo: FoodDetailInfo) {
        _state.update {
            it.copy(
                takenFoodList = it.takenFoodList.toMutableList().apply {
                    this.remove(foodDetailInfo)
                }
            )
        }
    }

    fun insertMealHistory() {
        viewModelScope.launch {
            upsertMealHistoryInfoUseCase(
                info = MealHistoryInfo(
                    takenDateTime = state.value.takenDateTime,
                    mealType = state.value.mealType,
                    foodList = state.value.takenFoodList
                ),
            )
        }
    }
}