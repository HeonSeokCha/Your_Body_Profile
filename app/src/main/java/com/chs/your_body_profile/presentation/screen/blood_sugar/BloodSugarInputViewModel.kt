package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.lifecycle.ViewModel
import com.chs.your_body_profile.domain.usecase.UpsertBloodSugarInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BloodSugarInputViewModel @Inject constructor(
    private val upsertBloodSugarInfoUseCase: UpsertBloodSugarInfoUseCase
) : ViewModel() {
}