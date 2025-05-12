package com.chs.your_body_profile.presentation.screen.blood_sugar.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.domain.usecase.UpsertBloodSugarInfoUseCase
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
class BloodSugarInputViewModel @Inject constructor(
    private val upsertBloodSugarInfoUseCase: UpsertBloodSugarInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BloodSugarInputState())
    val state = _state.asStateFlow()

    private val _effect: Channel<BaseEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    fun changeIntent(intent: BloodSugarInputEvent) {
        when (intent) {
            is BloodSugarInputEvent.ChangeDateTime -> {
                updateBloodSugarMeasureTime(intent.dateTime)
            }

            BloodSugarInputEvent.ChangeShowDateTimePicker -> {
                _state.update { it.copy(isShowDateTimePicker = !it.isShowDateTimePicker) }
            }

            is BloodSugarInputEvent.OnChangeBloodSugarLevel -> {
                updateBloodSugarNumber(intent.level)
            }

            is BloodSugarInputEvent.OnChangeMeasureType -> {
                updateBloodSugarMeasureType(intent.idx)
            }

            is BloodSugarInputEvent.OnChangeMemo -> {
                updateBloodSugarMemo(intent.memo)
            }

            BloodSugarInputEvent.OnClickSaveButton -> {
                upsertBloodSugarInfo()
            }

            BloodSugarInputEvent.OnBack -> Unit
        }
    }

    private fun updateBloodSugarMeasureTime(localDateTime: LocalDateTime) {
        _state.update {
            it.copy(
                measureDateTime = localDateTime,
                isShowDateTimePicker = false
            )
        }
    }

    private fun updateBloodSugarNumber(number: Int) {
        _state.update { it.copy(level = number) }
    }

    private fun updateBloodSugarMeasureType(idx: Int) {
        _state.update { it.copy(selectedMeasureIdx = idx) }
    }

    private fun updateBloodSugarMemo(text: String) {
        _state.update { it.copy(memo = text) }
    }

    private fun upsertBloodSugarInfo() {
        viewModelScope.launch {
            upsertBloodSugarInfoUseCase(
                BloodSugarInfo(
                    measureDateTime = state.value.measureDateTime,
                    measureTypeIdx = state.value.selectedMeasureIdx,
                    number = state.value.level,
                    memo = state.value.memo
                )
            )
            _effect.send(BaseEffect.OnBack)
        }
    }
}