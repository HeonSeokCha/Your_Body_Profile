package com.chs.your_body_profile.presentation.screen.blood_pressure

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class BloodPressureListState(
    val chartList: Flow<PagingData<Pair<LocalDate, Int>>>? = null,
    val selectDate: LocalDate = LocalDate.now(),
)
