package com.chs.your_body_profile.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkInfo
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
import com.chs.your_body_profile.domain.usecase.UpsertDrinkInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDayLastBloodPressureInfoUseCase: GetDayLastBloodPressureInfoUseCase,
    private val getDayLastBloodSugarInfoUseCase: GetDayLastBloodSugarInfoUseCase,
    private val getDayLastDrinkCoffeeInfoUseCase: GetDayLastDrinkCoffeeInfoUseCase,
    private val getDayLastDrinkWaterInfoUseCase: GetDayLastDrinkWaterInfoUseCase,
    private val getDayLastMedicineInfoUseCase: GetDayLastMedicineInfoUseCase,
    private val getDayLastHemoglobinA1cInfoUseCase: GetDayLastHemoglobinA1cInfoUseCase,
    private val getDayLastWeightInfoUseCase: GetDayLastWeightInfoUseCase,
    private val getDayLastTakenFoodInfoUseCase: GetDayLastTakenFoodInfoUseCase,
    private val getDayLastInsulinInfoUseCase: GetDayLastInsulinInfoUseCase,
    private val upsertDrinkInfoUseCase: UpsertDrinkInfoUseCase
) : ViewModel() {

    private val currentDate: LocalDate = LocalDate.now()

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
            getDayLastBloodPressureInfoUseCase(),
            getDayLastBloodSugarInfoUseCase(),
            getDayLastDrinkCoffeeInfoUseCase(currentDate),
            getDayLastDrinkWaterInfoUseCase(currentDate),
            getDayLastHemoglobinA1cInfoUseCase(),
            getDayLastMedicineInfoUseCase(),
            getDayLastTakenFoodInfoUseCase(currentDate),
            getDayLastWeightInfoUseCase(),
            getDayLastInsulinInfoUseCase()
        ) { list ->
            _state.update {
                it.copy(
                    bloodPressureInfo = (list[0] as BloodPressureInfo?),
                    bloodSugarInfo = (list[1] as BloodSugarInfo?),
                    drinkCoffeeTotalCupInfo = (list[2] as Int),
                    drinkWaterTotalCupInfo = (list[3] as Int),
                    hemoglobinA1cInfo = (list[4] as HemoglobinA1cInfo?),
                    medicineInfo = (list[5] as MedicineInfo?),
                    takenFoodInfo = (list[6] as FoodDetailInfo?),
                    weightInfo = (list[7] as WeightInfo?),
                    insulinInfo = (list[8] as InsulinInfo?)
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
            }

            is HomeEvent.Update.Water -> {
            }

            else -> Unit
        }
    }
}