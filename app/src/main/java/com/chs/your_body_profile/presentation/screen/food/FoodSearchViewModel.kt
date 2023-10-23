package com.chs.your_body_profile.presentation.screen.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.usecase.GetRecentFoodSearchHistoryUseCase
import com.chs.your_body_profile.domain.usecase.GetRecentTakenFoodsUseCase
import com.chs.your_body_profile.domain.usecase.GetSearchResultFoodInfoUseCase
import com.chs.your_body_profile.domain.usecase.UpsertFoodSearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodSearchViewModel @Inject constructor(
    private val getSearchResultFoodInfoUseCase: GetSearchResultFoodInfoUseCase,
    private val getRecentFoodSearchHistoryUseCase: GetRecentFoodSearchHistoryUseCase,
    private val getRecentTakenFoodsUseCase: GetRecentTakenFoodsUseCase,
    private val upsertFoodSearchHistoryUseCase: UpsertFoodSearchHistoryUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FoodSearchState())
    val state: StateFlow<FoodSearchState> = _state.asStateFlow()

    fun initInfo(mealType: String) {
        _state.update {
            it.copy(
                mealType = mealType
            )
        }
    }

    fun updateQuery(query: String) {
        _state.update {
            it.copy(
                searchQuery = query
            )
        }
    }

    fun searchFood() {
        viewModelScope.launch {
            if (_state.value.searchQuery.isNotBlank()) {
                _state.update {
                    it.copy(
                        searchResult = getSearchResultFoodInfoUseCase(_state.value.searchQuery)
                            .cachedIn(viewModelScope)
                    )
                }
            }
        }
    }

    fun getRecentFoodSearchHistory() {
        viewModelScope.launch {
            getRecentFoodSearchHistoryUseCase().collect { searchHistory ->
                _state.update {
                    it.copy(
                        searchHistory = searchHistory
                    )
                }
            }
        }
    }

    fun getRecentTakenFoods() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    recentFoodList = getRecentTakenFoodsUseCase()
                )
            }
        }
    }

    fun upsertFoodSearchHistory(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                upsertFoodSearchHistoryUseCase(query)
            }
        }
    }

    fun updateSelectItem(list: List<FoodDetailInfo>) {
        _state.update {
            it.copy(
                selectFoodList = list
            )
        }
    }
}