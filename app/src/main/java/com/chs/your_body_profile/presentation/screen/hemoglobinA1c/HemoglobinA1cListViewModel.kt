package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.usecase.GetPagingHemoglobinA1cUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HemoglobinA1cListViewModel @Inject constructor(
    private val getPagingHemoglobinA1cUseCase: GetPagingHemoglobinA1cUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<HemoglobinA1cListState> = MutableStateFlow(
        HemoglobinA1cListState()
    )
    val state: StateFlow<HemoglobinA1cListState> = _state.asStateFlow()

    init {
        getPagingInfo()
    }

    fun getPagingInfo() {
        _state.update {
            it.copy(
                pagingList = getPagingHemoglobinA1cUseCase().cachedIn(viewModelScope)
            )
        }
    }
}