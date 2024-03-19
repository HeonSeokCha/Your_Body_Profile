package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.usecase.GetPagingBloodSugarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BloodSugarListViewModel @Inject constructor(
    private val getPagingBloodSugarUseCase: GetPagingBloodSugarUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BloodSugarListState())
    val state = _state.asStateFlow()

    init {
        getPagingBloodSugar()
    }

    fun getPagingBloodSugar() {
        _state.update {
            it.copy(
                pagingList = getPagingBloodSugarUseCase().cachedIn(viewModelScope)
            )
        }
    }
}