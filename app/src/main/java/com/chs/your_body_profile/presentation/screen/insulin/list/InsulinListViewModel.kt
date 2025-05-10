package com.chs.your_body_profile.presentation.screen.insulin.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.usecase.GetPagingInsulinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InsulinListViewModel @Inject constructor(
   private val getPagingInsulinUseCase: GetPagingInsulinUseCase
): ViewModel() {

    private val _state: MutableStateFlow<InsulinListState> = MutableStateFlow(InsulinListState())
    val state: StateFlow<InsulinListState> = _state.asStateFlow()

    init {
        getPagingInfo()
    }

    fun getPagingInfo() {
        _state.update {
            it.copy(pagingList = getPagingInsulinUseCase().cachedIn(viewModelScope))
        }
    }
}