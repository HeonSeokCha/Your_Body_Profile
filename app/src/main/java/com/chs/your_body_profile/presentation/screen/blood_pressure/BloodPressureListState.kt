package com.chs.your_body_profile.presentation.screen.blood_pressure

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class BloodPressureListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, List<Pair<Int, Int>>>>>? = null,
    val selectInfo: BloodPressureInfo? = null
)
