package com.chs.your_body_profile.presentation.screen.food

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.usecase.GetDayMealTypeListUseCase
import com.chs.your_body_profile.domain.usecase.GetDayTakenListUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingTotalCalorieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val getPagingTotalCalorieUseCase: GetPagingTotalCalorieUseCase,
    private val getDayTakenListUseCase: GetDayTakenListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MealListState())
    val state = _state.asStateFlow()

    fun getPagingTotalCalories() {
        _state.update {
            it.copy(
                chartList = getPagingTotalCalorieUseCase().cachedIn(viewModelScope)
            )
        }
    }

    fun getDayTakenMealInfo(localDate: LocalDate) {
        viewModelScope.launch {
            getDayTakenListUseCase(localDate).collect { mealTypeList ->
                _state.update {
                    it.copy(dayTakenMealList = mealTypeList)
                }
            }
        }
    }

    fun updateSelectDate(localDate: LocalDate) {
        _state.update {
            it.copy(
                selectDate = localDate
            )
        }
    }
}