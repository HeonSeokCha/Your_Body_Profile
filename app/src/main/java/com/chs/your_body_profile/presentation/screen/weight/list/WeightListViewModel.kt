package com.chs.your_body_profile.presentation.screen.weight.list

import androidx.lifecycle.ViewModel
import com.chs.your_body_profile.domain.usecase.GetPagingWeightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WeightListViewModel @Inject constructor(
    private val getPagingWeightUseCase: GetPagingWeightUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(WeightListState())
    val sate = _state.asStateFlow()


}