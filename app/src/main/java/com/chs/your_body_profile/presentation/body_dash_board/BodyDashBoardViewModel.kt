package com.chs.your_body_profile.presentation.body_dash_board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.usecase.GetDayLastBloodPressureInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastBloodSugarInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastDrinkCoffeeInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastDrinkWaterInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastHemoglobinA1cInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastInsulinInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastMedicineInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastWeightInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class BodyDashBoardViewModel @Inject constructor(
    private val getDayLastBloodPressureInfoUseCase: GetDayLastBloodPressureInfoUseCase,
    private val getDayLastBloodSugarInfoUseCase: GetDayLastBloodSugarInfoUseCase,
    private val getDayLastDrinkCoffeeInfoUseCase: GetDayLastDrinkCoffeeInfoUseCase,
    private val getDayLastDrinkWaterInfoUseCase: GetDayLastDrinkWaterInfoUseCase,
    private val getDayLastMedicineInfoUseCase: GetDayLastMedicineInfoUseCase,
    private val getDayLastInsulinInfoUseCase: GetDayLastInsulinInfoUseCase,
    private val getDayLastHemoglobinA1cInfoUseCase: GetDayLastHemoglobinA1cInfoUseCase,
    private val getDayLastWeightInfoUseCase: GetDayLastWeightInfoUseCase
) : ViewModel() {

    companion object {
        val todayLocalDate: LocalDate = LocalDate.now()
    }

    private val _bloodPressureInfo = getDayLastBloodPressureInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _bloodSugarInfo = getDayLastBloodSugarInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _insulinInfo = getDayLastInsulinInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _drinkWaterInfo = getDayLastDrinkWaterInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _drinkCoffeeInfo = getDayLastDrinkCoffeeInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _hemoglobinA1cInfo = getDayLastHemoglobinA1cInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _medicineInfo = getDayLastMedicineInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _weightInfo = getDayLastWeightInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _state = MutableStateFlow(BodyDashBoardState())

}