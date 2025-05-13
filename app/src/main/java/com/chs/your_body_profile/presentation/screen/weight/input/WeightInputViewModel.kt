package com.chs.your_body_profile.presentation.screen.weight.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.usecase.UpsertWeightInfoUseCase
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
class WeightInputViewModel @Inject constructor(
    private val upsertWeightInfoUseCase: UpsertWeightInfoUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(WeightInputState())
    val state = _state.asStateFlow()

    private val _effect: Channel<BaseEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    fun changeIntent(intent: WeightInputEvent) {
        when (intent) {
            is WeightInputEvent.ChangeDateTime -> {
                updateMeasureTime(intent.dateTime)
            }

            WeightInputEvent.ChangeShowDateTimePicker -> {
                _state.update { it.copy(isShowDateTimePicker = !it.isShowDateTimePicker) }
            }

            is WeightInputEvent.OnChangeWeight -> {
                updateWeightInfo(intent.weight)
            }
            is WeightInputEvent.OnChangeMemo -> {
                updateMemo(intent.memo)
            }
            WeightInputEvent.OnClickSaveButton -> {
                upsertWeightInfo()
            }
            WeightInputEvent.OnBack -> Unit
        }
    }

    private fun updateWeightInfo(weight: Float) {
        viewModelScope.launch {
            _state.update { it.copy(weight = weight)}
        }
    }

    private fun updateMeasureTime(time: LocalDateTime) {
        _state.update {
            it.copy(
                measureDateTime = time,
                isShowDateTimePicker = false
            )
        }
    }

    private fun updateMemo(text: String) {
        _state.update { it.copy(memo = text) }
    }

    private fun upsertWeightInfo() {
        viewModelScope.launch {
            upsertWeightInfoUseCase(
                WeightInfo(
                    weight = state.value.weight,
                    memo = state.value.memo,
                    measureDateTime = state.value.measureDateTime
                )
            )

            _effect.send(BaseEffect.OnBack)
        }
    }
}