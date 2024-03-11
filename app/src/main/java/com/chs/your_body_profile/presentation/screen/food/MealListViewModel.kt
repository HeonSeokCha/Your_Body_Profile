package com.chs.your_body_profile.presentation.screen.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.usecase.DeleteMealInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingFoodInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val getPagingFoodInfoUseCase: GetPagingFoodInfoUseCase,
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
}