package com.chs.your_body_profile.presentation.body_dash_board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.usecase.GetDayLastBloodPressureInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastBloodSugarInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastDrinkCoffeeInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastDrinkWaterInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastHemoglobinA1cInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastInsulinInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastMedicineInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastWeightInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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

    val a = combine(_state, _bloodPressureInfo) { b, c->
        b

    }
    val state: Flow<BodyDashBoardState> = combine(
        _state,
        _bloodPressureInfo,
        _bloodSugarInfo,
        _insulinInfo,
        _drinkCoffeeInfo,
        _drinkWaterInfo,
        _hemoglobinA1cInfo,
        _medicineInfo,
        _weightInfo
    ) { list ->
        val sate: BodyDashBoardState = (list[0] as BodyDashBoardState)
            sate.copy(
                bloodPressureInfo = (list[0] as BloodPressureInfo?),
                bloodSugarInfo = (list[1] as BloodSugarInfo?),
                insulinInfo = (list[2] as InsulinInfo?),
                drinkCoffeeInfo = (list[3] as DrinkCoffeeInfo?),
                drinkWaterInfo = (list[4] as DrinkWaterInfo?),
                hemoglobinA1cInfo = (list[5] as HemoglobinA1cInfo?),
                medicineInfo = (list[6] as MedicineInfo?),
                weightInfo = (list[7] as WeightInfo?)
            )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), BodyDashBoardState())
}