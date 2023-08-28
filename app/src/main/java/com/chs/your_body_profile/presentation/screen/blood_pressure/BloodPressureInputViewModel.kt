package com.chs.your_body_profile.presentation.screen.blood_pressure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.usecase.UpsertBloodPressureInfoUseCase
import com.chs.your_body_profile.domain.usecase.UpsertBloodSugarInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class BloodPressureInputViewModel @Inject constructor(
    private val upsertBloodPressureInfoUseCase: UpsertBloodPressureInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BloodPressureInputState())

    fun insertBloodPressureInfo() {
        viewModelScope.launch {
            upsertBloodPressureInfoUseCase(
                BloodPressureInfo(
                    measureTime = _state.value.measureTime!!,
                    systolic = _state.value.systolic,
                    diastolic = _state.value.diastolic,
                    memo = _state.value.memo
                )
            )
        }
    }

    fun updateSystolicNumber(number: Int) {
        _state.update {
            it.copy(
                systolic = number
            )
        }
    }

    fun updateDiastolicNumber(number: Int) {
        _state.update {
            it.copy(
                diastolic = number
            )
        }
    }

    fun updateMemo(text: String) {
        _state.update {
            it.copy(
                memo = text
            )
        }
    }

    fun updateMeasureTime(localDateTime: LocalDateTime) {
        _state.update {
            it.copy(
                measureTime = localDateTime
            )
        }
    }
}