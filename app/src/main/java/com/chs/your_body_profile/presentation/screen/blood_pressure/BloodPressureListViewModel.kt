package com.chs.your_body_profile.presentation.screen.blood_pressure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.usecase.GetPagingBloodPressureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BloodPressureListViewModel @Inject constructor(
    private val getPagingBloodPressureUseCase: GetPagingBloodPressureUseCase
): ViewModel() {

    private val _state: MutableStateFlow<BloodPressureListState> = MutableStateFlow(BloodPressureListState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                pagingList = getPagingBloodPressureUseCase()
                    .cachedIn(viewModelScope)
            )
        }
    }

    fun selectInfo(bloodPressureInfo: BloodPressureInfo) {
        _state.update {
            it.copy(
                selectInfo = bloodPressureInfo
            )
        }
    }
}