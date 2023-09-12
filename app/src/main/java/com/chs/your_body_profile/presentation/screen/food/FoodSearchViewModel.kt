package com.chs.your_body_profile.presentation.screen.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.compose.collectAsLazyPagingItems
import com.chs.your_body_profile.domain.usecase.GetSearchResultFoodInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodSearchViewModel @Inject constructor(
    private val getSearchResultFoodInfoUseCase: GetSearchResultFoodInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FoodSearchState())
    val state: StateFlow<FoodSearchState> = _state.asStateFlow()

    fun updateQuery(query: String) {
        _state.update {
            it.copy(
                searchQuery = query
            )
        }
    }

    fun searchFood() {
        viewModelScope.launch {
            if (!_state.value.searchQuery.isNullOrBlank()) {
                _state.update {
                    it.copy(
                        searchResult = getSearchResultFoodInfoUseCase(_state.value.searchQuery!!)
                    )
                }
            }
        }
    }
}