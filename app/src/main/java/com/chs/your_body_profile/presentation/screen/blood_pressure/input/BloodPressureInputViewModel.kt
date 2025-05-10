package com.chs.your_body_profile.presentation.screen.blood_pressure.input

import androidx.compose.ui.util.fastCbrt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.usecase.UpsertBloodPressureInfoUseCase
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
    val state = _state.asStateFlow()

    fun changeEvent(event: BloodPressureInputEvent) {
        when (event) {
            BloodPressureInputEvent.ChangeShowDateTimePicker -> {
                _state.update {
                    it.copy(
                        isShowDateTimePicker = !it.isShowDateTimePicker
                    )
                }
            }

            is BloodPressureInputEvent.ChangeDateTime -> {
                _state.update {
                    it.copy(
                        measureDateTime = event.dateTime,
                        isShowDateTimePicker = false
                    )
                }
            }

            else -> Unit
        }
    }

    fun initMeasureInfo(
        bloodPressureInfo: BloodPressureInfo
    ) {
        _state.update {
            it.copy(
                measureDateTime = bloodPressureInfo.measureDateTime,
                diastolic = bloodPressureInfo.diastolic,
                systolic = bloodPressureInfo.systolic,
                memo = bloodPressureInfo.memo
            )
        }
    }

    fun insertBloodPressureInfo() {
        viewModelScope.launch {
            upsertBloodPressureInfoUseCase(
                BloodPressureInfo(
                    measureDateTime = _state.value.measureDateTime,
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
                measureDateTime = localDateTime
            )
        }
    }
}