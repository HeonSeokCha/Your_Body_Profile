package com.chs.your_body_profile.presentation.screen.insulin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.usecase.UpsertInsulinInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsulinInputViewModel @Inject constructor(
    private val upsertInsulinInfoUseCase: UpsertInsulinInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(InsulinInputState())

    fun updateInsulinLevel(level: Int) {
        _state.update {
            it.copy(
                level = level
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

    fun insertInsulinInfo() {
        viewModelScope.launch {
            upsertInsulinInfoUseCase(
                InsulinInfo(
                    level = _state.value.level,
                    memo = _state.value.memo
                )
            )
        }
    }
}