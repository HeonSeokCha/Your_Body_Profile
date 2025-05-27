package com.chs.your_body_profile.presentation.screen.weight.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.usecase.GetPagingWeightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WeightListViewModel @Inject constructor(
    private val getPagingWeightUseCase: GetPagingWeightUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(WeightListState())
    val state = _state
        .onStart {
            getPagingList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            WeightListState()
        )

    fun changeIntent(intent: WeightListEvent) {
        when (intent) {
            is WeightListEvent.OnChangeSelectIdx -> changeIdx(intent.idx)
            is WeightListEvent.OnSelectInfo -> changeInfoList(intent.info)
            else -> Unit
        }
    }

    private fun getPagingList() {
        _state.update {
            it.copy(
                pagingList = getPagingWeightUseCase().cachedIn(viewModelScope)
            )
        }
    }

    private fun changeInfoList(info: List<WeightInfo>) {
        _state.update { it.copy(selectInfo = info) }
    }

    private fun changeIdx(idx: Int) {
        _state.update { it.copy(selectIdx = idx) }
    }
}