package com.chs.your_body_profile.presentation.screen.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.usecase.DeleteMealInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayTakenListUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingFoodInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val getPagingFoodInfoUseCase: GetPagingFoodInfoUseCase,
    private val getDayTakenListUseCase: GetDayTakenListUseCase,
    private val deleteTakenMealInfoUseCase: DeleteMealInfoUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MealListState())
    val state = _state.asStateFlow()

    fun getPagingTotalCalories() {
        _state.update {
            it.copy(
                pagingList = getPagingFoodInfoUseCase().cachedIn(viewModelScope)
            )
        }
    }

    fun getDayTakenList() {
        viewModelScope.launch {
            getDayTakenListUseCase(state.value.selectDate).collect { infoList ->
                _state.update {
                    it.copy(
                        dayTakenList = infoList
                    )
                }
            }
        }
    }

    fun updateSelectDate(localDate: LocalDate) {
        _state.update { it.copy(selectDate = localDate) }
    }
}