package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.input

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.usecase.UpsertHemoglobinA1cInfoUseCase
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
class HemoglobinA1cInputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val upsertHemoglobinA1cInfoUseCase: UpsertHemoglobinA1cInfoUseCase
) : ViewModel() {
    private val lastInputNumber: Float =
        savedStateHandle.toRoute<Screens.HemoglobinA1cInput>().info ?: 5.5f

    private val _state = MutableStateFlow(HemoglobinA1cInputState(number = lastInputNumber))
    val state = _state.asStateFlow()

    private val _effect: Channel<BaseEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    fun changeIntent(intent: HemoglobinA1cInputEvent) {
        when (intent) {
            is HemoglobinA1cInputEvent.ChangeDateTime -> {
                updateMeasureTime(intent.dateTime)
            }

            HemoglobinA1cInputEvent.ChangeShowDateTimePicker -> {
                _state.update { it.copy(isShowDateTimePicker = !it.isShowDateTimePicker) }
            }

            is HemoglobinA1cInputEvent.OnChangeHA1cInfo -> {
                updateHemoglobinA1cNumber(intent.level)
            }

            is HemoglobinA1cInputEvent.OnChangeMemo -> {
                updateMemo(intent.memo)
            }

            HemoglobinA1cInputEvent.OnClickSaveButton -> {
                upsertHemoglobinA1c()
            }

            HemoglobinA1cInputEvent.OnBack -> Unit
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

    private fun upsertHemoglobinA1c() {
        viewModelScope.launch {
            upsertHemoglobinA1cInfoUseCase(
                HemoglobinA1cInfo(
                    number = state.value.number,
                    memo = state.value.memo,
                    measureHospital = state.value.measureHospital ?: "",
                    measureDate = state.value.measureDateTime
                )
            )
            _effect.send(BaseEffect.OnBack)
        }
    }

    private fun updateHemoglobinA1cNumber(number: Float) {
        _state.update { it.copy(number = number) }
    }

    private fun updateMemo(text: String) {
        _state.update { it.copy(memo = text) }
    }
}