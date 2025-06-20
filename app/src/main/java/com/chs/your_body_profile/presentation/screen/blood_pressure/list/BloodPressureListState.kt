package com.chs.your_body_profile.presentation.screen.blood_pressure.list

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class BloodPressureListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, List<BloodPressureInfo>>>>? = null,
    val selectIdx: Int = 0,
    val selectListInfo: List<BloodPressureInfo> = emptyList(),
    val selectInfo: BloodPressureInfo? = null,
    val showRemoveDialog: Boolean = false,
    val showDetailDialog: Boolean = false
)
