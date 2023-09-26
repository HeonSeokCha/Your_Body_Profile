package com.chs.your_body_profile.presentation.screen.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodHistoryInputViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow(FoodHistoryState())
    val state = _state.asStateFlow()

    fun getTakeMealList() {
        viewModelScope.launch {

        }
    }
}