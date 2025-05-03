package com.chs.your_body_profile.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.FoodDetailInfo
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
import com.chs.your_body_profile.domain.usecase.GetDayLastTakenFoodInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetDayLastWeightInfoUseCase
import com.chs.your_body_profile.domain.usecase.UpsertDrinkCoffeeInfoUseCase
import com.chs.your_body_profile.domain.usecase.UpsertDrinkWaterInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDayLastBloodPressureInfoUseCase: GetDayLastBloodPressureInfoUseCase,
    private val getDayLastBloodSugarInfoUseCase: GetDayLastBloodSugarInfoUseCase,
    private val getDayLastDrinkCoffeeInfoUseCase: GetDayLastDrinkCoffeeInfoUseCase,
    private val getDayLastDrinkWaterInfoUseCase: GetDayLastDrinkWaterInfoUseCase,
    private val getDayLastMedicineInfoUseCase: GetDayLastMedicineInfoUseCase,
    private val getDayLastInsulinInfoUseCase: GetDayLastInsulinInfoUseCase,
    private val getDayLastHemoglobinA1cInfoUseCase: GetDayLastHemoglobinA1cInfoUseCase,
    private val getDayLastWeightInfoUseCase: GetDayLastWeightInfoUseCase,
    private val getDayLastTakenFoodInfoUseCase: GetDayLastTakenFoodInfoUseCase,
    private val upsertDrinkWaterInfoUseCase: UpsertDrinkWaterInfoUseCase,
    private val upsertDrinkCoffeeInfoUseCase: UpsertDrinkCoffeeInfoUseCase
) : ViewModel() {

    private val currentDate: LocalDateTime = LocalDateTime.now()

    private val _state = MutableStateFlow(HomeState())

    val state: StateFlow<HomeState> = _state
        .onStart { initInfo() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            HomeState()
        )

    private fun initInfo() {
        combine(
            getDayLastBloodPressureInfoUseCase(currentDate),
            getDayLastBloodSugarInfoUseCase(currentDate),
            getDayLastInsulinInfoUseCase(currentDate),
            getDayLastDrinkCoffeeInfoUseCase(currentDate),
            getDayLastDrinkWaterInfoUseCase(currentDate),
            getDayLastHemoglobinA1cInfoUseCase(currentDate),
            getDayLastMedicineInfoUseCase(currentDate),
            getDayLastTakenFoodInfoUseCase(currentDate),
            getDayLastWeightInfoUseCase(currentDate)
        ) { list ->
            _state.update {
                it.copy(
                    bloodPressureInfo = (list[0] as BloodPressureInfo?),
                    bloodSugarInfo = (list[1] as BloodSugarInfo?),
                    insulinInfo = (list[2] as InsulinInfo?),
                    drinkCoffeeInfo = (list[3] as DrinkCoffeeInfo?),
                    drinkWaterInfo = (list[4] as DrinkWaterInfo?),
                    hemoglobinA1cInfo = (list[5] as HemoglobinA1cInfo?),
                    medicineInfo = (list[6] as MedicineInfo?),
                    takenFoodInfo = (list[7] as FoodDetailInfo?),
                    weightInfo = (list[8] as WeightInfo?)
                )
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            HomeState()
        )
    }

    fun changeEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Update.Coffee -> {
                updateDrinkCoffeeInfo(event.cups)
            }

            is HomeEvent.Update.Water -> {
                updateDrinkWaterInfo(event.cups)
            }

            else -> Unit
        }
    }

    private fun updateDrinkCoffeeInfo(totalCups: Int) {
        viewModelScope.launch {
            upsertDrinkCoffeeInfoUseCase(
                DrinkCoffeeInfo(
                    takenDateTime = currentDate.toLocalDate(),
                    totalCups = totalCups
                )
            )
        }
    }

    private fun updateDrinkWaterInfo(totalCups: Int) {
        viewModelScope.launch {
            upsertDrinkWaterInfoUseCase(
                DrinkWaterInfo(
                    takenDateTime = currentDate.toLocalDate(),
                    totalCups = totalCups
                )
            )
        }
    }
}