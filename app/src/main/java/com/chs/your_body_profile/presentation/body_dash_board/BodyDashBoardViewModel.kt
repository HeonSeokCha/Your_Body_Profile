package com.chs.your_body_profile.presentation.body_dash_board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.MedicineType
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.usecase.GetDayLastBloodPressureInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastBloodSugarInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastDrinkCoffeeInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastDrinkWaterInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastFoodInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastHemoglobinA1cInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastInsulinInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastMedicineInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastWeightInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayTotalCalorieUseCase
import com.chs.your_body_profile.domain.usecase.UpsertDrinkCoffeeInfoUseCase
import com.chs.your_body_profile.domain.usecase.UpsertDrinkWaterInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class BodyDashBoardViewModel @Inject constructor(
    getDayLastBloodPressureInfoUseCase: GetDayLastBloodPressureInfoUseCase,
    getDayLastBloodSugarInfoUseCase: GetDayLastBloodSugarInfoUseCase,
    getDayLastDrinkCoffeeInfoUseCase: GetDayLastDrinkCoffeeInfoUseCase,
    getDayLastDrinkWaterInfoUseCase: GetDayLastDrinkWaterInfoUseCase,
    getDayLastMedicineInfoUseCase: GetDayLastMedicineInfoUseCase,
    getDayLastInsulinInfoUseCase: GetDayLastInsulinInfoUseCase,
    getDayLastHemoglobinA1cInfoUseCase: GetDayLastHemoglobinA1cInfoUseCase,
    getDayLastWeightInfoUseCase: GetDayLastWeightInfoUseCase,
    getDayLastFoodInfoUseCase: GetDayLastFoodInfoUseCase,
    getDayTotalCalorieUseCase: GetDayTotalCalorieUseCase,
    private val upsertDrinkWaterInfoUseCase: UpsertDrinkWaterInfoUseCase,
    private val upsertDrinkCoffeeInfoUseCase: UpsertDrinkCoffeeInfoUseCase
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

    private val _foodInfo = getDayLastFoodInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _dayTotalCalorie = getDayTotalCalorieUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    private val _weightInfo = getDayLastWeightInfoUseCase(todayLocalDate)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _state = MutableStateFlow(BodyDashBoardState())

    val state: Flow<BodyDashBoardState> = combine(
        _state,
        _bloodPressureInfo,
        _bloodSugarInfo,
        _insulinInfo,
        _drinkCoffeeInfo,
        _drinkWaterInfo,
        _hemoglobinA1cInfo,
        _medicineInfo,
        _foodInfo,
        _dayTotalCalorie,
        _weightInfo
    ) { list ->
        (list[0] as BodyDashBoardState).run {
            this.copy(
                bloodPressureInfo = (list[1] as BloodPressureInfo?),
                bloodSugarInfo = (list[2] as BloodSugarInfo?),
                insulinInfo = (list[3] as InsulinInfo?),
                drinkCoffeeInfo = (list[4] as DrinkCoffeeInfo?),
                drinkWaterInfo = (list[5] as DrinkWaterInfo?),
                hemoglobinA1cInfo = (list[6] as HemoglobinA1cInfo?),
                medicineInfo = (list[7] as MedicineInfo?),
                foodInfo = (list[8] as FoodInfo?),
                totalCalorie = (list[9] as Int),
                weightInfo = (list[10] as WeightInfo?)
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), BodyDashBoardState())

    fun updateDrinkCoffeeInfo(totalCups: Int) {
        viewModelScope.launch {
            upsertDrinkCoffeeInfoUseCase(
                _state.value.drinkCoffeeInfo?.copy(
                    totalCups = totalCups
                ) ?: DrinkCoffeeInfo(totalCups = totalCups)
            )
        }
    }

    fun updateDrinkWaterInfo(totalCups: Int) {
        viewModelScope.launch {
            upsertDrinkWaterInfoUseCase(
                _state.value.drinkWaterInfo?.copy(
                    totalCups = totalCups
                ) ?: DrinkWaterInfo(totalCups = totalCups)
            )
        }
    }
}