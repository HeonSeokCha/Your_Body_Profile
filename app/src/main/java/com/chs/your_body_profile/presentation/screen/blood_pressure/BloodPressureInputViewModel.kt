package com.chs.your_body_profile.presentation.screen.blood_pressure

import androidx.lifecycle.ViewModel
import com.chs.your_body_profile.domain.usecase.UpsertBloodPressureInfoUseCase
import com.chs.your_body_profile.domain.usecase.UpsertBloodSugarInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BloodPressureInputViewModel @Inject constructor(
    private val upsertBloodPressureInfoUseCase: UpsertBloodPressureInfoUseCase
) : ViewModel() {

    suspend fun insertBloodPressureInfo() {

    }

}