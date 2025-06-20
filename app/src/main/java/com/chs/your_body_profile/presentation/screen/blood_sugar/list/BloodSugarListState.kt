package com.chs.your_body_profile.presentation.screen.blood_sugar.list

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class BloodSugarListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, List<BloodSugarInfo>>>>? = null,
    val selectIdx: Int = 0,
    val selectListInfo: List<BloodSugarInfo> = emptyList(),
    val selectInfo: BloodSugarInfo? = null,
    val showRemoveDialog: Boolean = false,
    val showDetailDialog: Boolean = false
)
