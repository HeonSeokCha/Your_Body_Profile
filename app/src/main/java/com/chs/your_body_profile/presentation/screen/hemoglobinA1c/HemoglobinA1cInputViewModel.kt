package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.usecase.UpsertHemoglobinA1cInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HemoglobinA1cInputViewModel @Inject constructor(
    private val upsertHemoglobinA1cInfoUseCase: UpsertHemoglobinA1cInfoUseCase
) : ViewModel() {

    private val state = MutableStateFlow(HemoglobinA1cInputState())

    fun insertHemoglobinA1c() {
        viewModelScope.launch {
            upsertHemoglobinA1cInfoUseCase(
                HemoglobinA1cInfo(
                    number = state.value.number,
                    memo = state.value.memo,
                    measureHospital = state.value.measureHospital ?: "",
                    measureDate = LocalDateTime.now()
                )
            )
        }
    }

    fun updateHemoglobinA1cNumber(number: Float) {
        state.update {
            it.copy(
                number = number
            )
        }
    }

    fun updateMeasurePlaceInfo(text: String) {
        state.update {
            it.copy(
                measureHospital = text
            )
        }
    }

    fun updateMemo(text: String) {
        state.update {
            it.copy(
                memo = text
            )
        }
    }
}