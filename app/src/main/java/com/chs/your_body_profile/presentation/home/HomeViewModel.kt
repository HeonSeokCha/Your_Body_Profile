package com.chs.your_body_profile.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.BodyMeasureInfo
import com.chs.your_body_profile.domain.usecase.GetBodyMeasureListUseCase
import com.chs.your_body_profile.domain.usecase.UpdateBodyMeasureListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBodyMeasureListUseCase: GetBodyMeasureListUseCase,
    private val updateBodyMeasureListUseCase: UpdateBodyMeasureListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getBodyMeasureList()
    }

    fun getBodyMeasureList() {
        viewModelScope.launch {
            getBodyMeasureListUseCase().collect { bodyMeasureList ->
                Log.e("TEST", bodyMeasureList.toString())
                _state.update {
                    it.copy(
                        bodyMeasureList = bodyMeasureList
                    )
                }
            }
        }
    }

    fun updateBodyMeasureModifyTime(bodyMeasureInfo: BodyMeasureInfo) {
        viewModelScope.launch {
            updateBodyMeasureListUseCase(
                bodyMeasureInfo.copy(
                    lastModifyTme = System.currentTimeMillis()
                )
            )
        }
    }
}