package com.chs.your_body_profile.presentation.screen.blood_pressure.input

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.usecase.UpsertBloodPressureInfoUseCase
import com.chs.your_body_profile.presentation.Screens
import com.chs.your_body_profile.presentation.screen.BaseEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class BloodPressureInputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val upsertBloodPressureInfoUseCase: UpsertBloodPressureInfoUseCase
) : ViewModel() {
    private val lastInputDiastolic: Int =
        savedStateHandle.toRoute<Screens.BloodPressureInput>().diastolic ?: 80
    private val lastInputSystolic: Int =
        savedStateHandle.toRoute<Screens.BloodPressureInput>().systolic ?: 120

    private val _state = MutableStateFlow(
        BloodPressureInputState(
            systolic = lastInputSystolic,
            diastolic = lastInputDiastolic
        )
    )

    val state = _state.asStateFlow()

    private val _effect: Channel<BaseEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    fun changeIntent(intent: BloodPressureInputEvent) {
        when (intent) {
            BloodPressureInputEvent.ChangeShowDateTimePicker -> {
                _state.update {
                    it.copy(isShowDateTimePicker = !it.isShowDateTimePicker)
                }
            }

            is BloodPressureInputEvent.OnChangeMemo -> {
                updateMemo(intent.memo)
            }

            is BloodPressureInputEvent.OnChangeSystolic -> {
                updateSystolicNumber(intent.value)
            }

            is BloodPressureInputEvent.OnChangeDiastolic -> {
                updateDiastolicNumber(intent.value)
            }

            is BloodPressureInputEvent.ChangeDateTime -> {
                updateMeasureTime(intent.dateTime)
            }

            BloodPressureInputEvent.OnClickSaveButton -> {
                upsertBloodPressureInfo()
            }

            else -> Unit
        }
    }

    private fun initMeasureInfo(bloodPressureInfo: BloodPressureInfo) {
        _state.update {
            it.copy(
                measureDateTime = bloodPressureInfo.measureDateTime,
                diastolic = bloodPressureInfo.diastolic,
                systolic = bloodPressureInfo.systolic,
                memo = bloodPressureInfo.memo
            )
        }
    }

    private fun upsertBloodPressureInfo() {
        viewModelScope.launch {
            upsertBloodPressureInfoUseCase(
                BloodPressureInfo(
                    measureDateTime = _state.value.measureDateTime,
                    systolic = _state.value.systolic,
                    diastolic = _state.value.diastolic,
                    memo = _state.value.memo
                )
            )
            _effect.send(BaseEffect.OnBack)
        }
    }

    private fun updateSystolicNumber(number: Int) {
        _state.update { it.copy(systolic = number) }
    }

    private fun updateDiastolicNumber(number: Int) {
        _state.update { it.copy(diastolic = number) }
    }

    private fun updateMemo(text: String) {
        _state.update { it.copy(memo = text) }
    }

    private fun updateMeasureTime(localDateTime: LocalDateTime) {
        _state.update {
            it.copy(
                measureDateTime = localDateTime,
                isShowDateTimePicker = false
            )
        }
    }
}