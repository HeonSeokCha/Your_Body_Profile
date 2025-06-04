package com.chs.your_body_profile.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.usecase.DeleteDrinkInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetLasPayInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetLastBloodPressureInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetLastBloodSugarInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetLastDrinkCoffeeInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetLastDrinkWaterInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetLastHemoglobinA1cInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetLastInsulinInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetLastMedicineInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetLastWeightInfoUseCase
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
    private val getLastBloodPressureInfoUseCase: GetLastBloodPressureInfoUseCase,
    private val getLastBloodSugarInfoUseCase: GetLastBloodSugarInfoUseCase,
    private val getLastDrinkCoffeeInfoUseCase: GetLastDrinkCoffeeInfoUseCase,
    private val getLastDrinkWaterInfoUseCase: GetLastDrinkWaterInfoUseCase,
    private val getLastMedicineInfoUseCase: GetLastMedicineInfoUseCase,
    private val getLastHemoglobinA1CInfoUseCase: GetLastHemoglobinA1cInfoUseCase,
    private val getLastWeightInfoUseCase: GetLastWeightInfoUseCase,
    private val getLastInsulinInfoUseCase: GetLastInsulinInfoUseCase,
    private val getLastPayInfoUseCase: GetLasPayInfoUseCase,
    private val upsertDrinkInfoUseCase: UpsertDrinkInfoUseCase,
    private val deleteDrinkInfoUseCase: DeleteDrinkInfoUseCase
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
            getLastBloodPressureInfoUseCase(),
            getLastBloodSugarInfoUseCase(),
            getLastDrinkCoffeeInfoUseCase(currentDate),
            getLastDrinkWaterInfoUseCase(currentDate),
            getLastHemoglobinA1CInfoUseCase(),
            getLastMedicineInfoUseCase(),
            getLastWeightInfoUseCase(),
            getLastInsulinInfoUseCase(),
            getLastPayInfoUseCase()
        ) { list ->
            _state.update {
                it.copy(
                    bloodPressureInfo = (list[0] as BloodPressureInfo?),
                    bloodSugarInfo = (list[1] as BloodSugarInfo?),
                    drinkCoffeeTotalCupInfo = (list[2] as List<DrinkInfo>),
                    drinkWaterTotalCupInfo = (list[3] as List<DrinkInfo>),
                    hemoglobinA1cInfo = (list[4] as HemoglobinA1cInfo?),
                    medicineInfo = (list[5] as MedicineInfo?),
                    weightInfo = (list[6] as WeightInfo?),
                    insulinInfo = (list[7] as InsulinInfo?),
                    payInfo = (list[8] as PaymentInfo?),
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
            HomeEvent.Update.Up.Coffee -> insertDrinkCoffeeInfo()
            HomeEvent.Update.Down.Coffee -> deleteLastDrinkCoffeeInfo()
            HomeEvent.Update.Up.Water -> insertDrinkWaterInfo()
            HomeEvent.Update.Down.Water -> deleteLastDrinkWaterInfo()

            else -> Unit
        }
    }

    private fun deleteLastDrinkWaterInfo() {
        if (_state.value.drinkWaterTotalCupInfo.isEmpty()) return
        viewModelScope.launch {
            deleteDrinkInfoUseCase(_state.value.drinkWaterTotalCupInfo.last())
        }
    }

    private fun deleteLastDrinkCoffeeInfo() {
        if (_state.value.drinkCoffeeTotalCupInfo.isEmpty()) return
        viewModelScope.launch {
            deleteDrinkInfoUseCase(_state.value.drinkCoffeeTotalCupInfo.last())
        }
    }

    private fun insertDrinkCoffeeInfo() {
        viewModelScope.launch {
            upsertDrinkInfoUseCase(
                DrinkInfo(takenDateTime = LocalDateTime.now(), DrinkType.COFFEE)
            )
        }
    }

    private fun insertDrinkWaterInfo() {
        viewModelScope.launch {
            upsertDrinkInfoUseCase(
                DrinkInfo(takenDateTime = LocalDateTime.now(), DrinkType.WATER)
            )
        }
    }
}