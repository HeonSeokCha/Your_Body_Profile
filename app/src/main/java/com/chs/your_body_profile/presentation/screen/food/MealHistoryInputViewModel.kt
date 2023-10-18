package com.chs.your_body_profile.presentation.screen.food

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.model.TakenMealInfo
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
    private val upsertMealHistoryInfoUseCase: UpsertMealHistoryInfoUseCase,
    private val upsertFoodDetailInfoUseCase: UpsertFoodDetailInfoUseCase,
    private val getDayMealTypeListUseCase: GetDayMealTypeListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MealHistoryInputState())
    val state = _state.asStateFlow()

    fun initMealHistoryInfo(
        takenDate: LocalDate,
        takenMealType: MealType,
        foodList: List<FoodDetailInfo>
    ) {
        viewModelScope.launch {
            getDayMealTypeListUseCase(
                takenDate = takenDate,
                mealType = takenMealType
            ).collect { takenInfo ->
                Log.e("TAKE", takenInfo.toString())
                _state.update {
                    if (takenInfo.first != null) {
                        it.copy(
                            takenDate = takenInfo.first!!.takenDate,
                            takenTime = takenInfo.first!!.takenTime,
                            mealType = takenInfo.first?.mealType,
                            takenFoodList = takenInfo.second.toMutableList().apply {
                                this.addAll(foodList)
                            }
                        )
                    } else {
                        it.copy(
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

    }

    fun insertMealHistory() {
        viewModelScope.launch {
            upsertMealHistoryInfoUseCase(
                info = TakenMealInfo(
                    takenDate = state.value.takenDate,
                    takenTime = state.value.takenTime,
                    mealType = state.value.mealType!!
                ),
                foodCodeList = state.value.takenFoodList.map { it.code }
            )

            upsertFoodDetailInfoUseCase(foodInfoList = state.value.takenFoodList)
        }
    }
}