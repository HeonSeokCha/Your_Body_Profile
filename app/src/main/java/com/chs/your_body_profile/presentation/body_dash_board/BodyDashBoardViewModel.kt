package com.chs.your_body_profile.presentation.body_dash_board

import androidx.lifecycle.ViewModel
import com.chs.your_body_profile.domain.usecase.GetDayLastBloodPressureInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BodyDashBoardViewModel @Inject constructor(
    private val getDayLastBloodPressureInfoUseCase: GetDayLastBloodPressureInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BodyDashBoardState())
    val state = _state.asStateFlow()

    fun getDayLastBloodPressureInfo() {

    }
}