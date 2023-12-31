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
import com.chs.your_body_profile.domain.usecase.UpsertFoodDetailInfoUseCase
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
    private val upsertFoodDetailInfoUseCase: UpsertFoodDetailInfoUseCase,
    private val getDayMealTypeListUseCase: GetDayMealTypeListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MealHistoryInputState())
    val state = _state.asStateFlow()

    private val takenDate: LocalDate = (savedStateHandle[Constants.ARG_TAKEN_DATE] ?: 0L).toLocalDate()
    private val takenMealType: MealType = MealType.values().find { mealType ->
        mealType.mean.second == savedStateHandle[Constants.ARG_TAKEN_MEAL_TYPE]
    } ?: MealType.MORNING

    fun initMealHistoryInfo(
        foodList: List<FoodDetailInfo>
    ) {
        viewModelScope.launch {
            getDayMealTypeListUseCase(
                takenDate = takenDate,
                mealType = takenMealType
            ).collect { takenInfo ->
                _state.update {
                    if (takenInfo.first != null) {
                        it.copy(
                            takenDate = takenDate,
                            takenTime = takenInfo.first!!.takenTime,
                            mealType = takenMealType,
                            previousMealHistory = takenInfo.second,
                            takenFoodList = foodList
                        )
                    } else {
                        it.copy(
                            takenDate = takenDate,
                            mealType = takenMealType,
                            takenFoodList = foodList
                        )
                    }
                }
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
                takenTime = localDateTime
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
                    takenDate = state.value.takenDate,
                    takenTime = state.value.takenTime,
                    mealType = state.value.mealType
                ),
                foodCodeList = state.value.takenFoodList.map { it.code }
            )

            upsertFoodDetailInfoUseCase(foodInfoList = state.value.takenFoodList)
        }
    }
}