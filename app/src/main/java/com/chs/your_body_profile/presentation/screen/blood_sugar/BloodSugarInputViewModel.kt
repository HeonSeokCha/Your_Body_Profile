package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.domain.usecase.UpsertBloodSugarInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class BloodSugarInputViewModel @Inject constructor(
    private val upsertBloodSugarInfoUseCase: UpsertBloodSugarInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BloodSugarInputState())
    val state = _state.asStateFlow()

    fun changeEvent(event: BloodSugarInputEvent) {

    }

    fun updateBloodSugarMeasureTime(localDateTime: LocalDateTime) {
        _state.update {
            it.copy(measureDateTime = localDateTime)
        }
    }

    fun updateBloodSugarNumber(number: Int) {
        _state.update {
            it.copy(
                level = number
            )
        }
    }

    fun updateBloodSugarMeasureType(type: MeasureType) {
        _state.update {
            it.copy(
                measureType = type
            )
        }
    }

    fun updateBloodSugarMemo(text: String) {
        _state.update {
            it.copy(
                memo = text
            )
        }
    }

    fun insertBloodSugarInfo() {
        viewModelScope.launch {
            upsertBloodSugarInfoUseCase(
                BloodSugarInfo(
                    measureDateTime = state.value.measureDateTime,
                    measureType = state.value.measureType!!,
                    number = state.value.level,
                    memo = state.value.memo
                )
            )
        }
    }
}