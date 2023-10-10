package com.chs.your_body_profile.presentation.screen.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.usecase.GetDayMealTypeListUseCase
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
    private val getDayMealTypeListUseCase: GetDayMealTypeListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MealListState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                chartList = getPagingTotalCalorieUseCase().cachedIn(viewModelScope)
            )
        }

        getDayTakenMealInfo(LocalDate.now())
    }

    fun getDayTakenMealInfo(localDate: LocalDate) {
        viewModelScope.launch {
            getDayMealTypeListUseCase(localDate).collect { mealTypeList ->
                _state.update {
                    it.copy(dayTakenMealList = mealTypeList)
                }
            }
        }
    }
}