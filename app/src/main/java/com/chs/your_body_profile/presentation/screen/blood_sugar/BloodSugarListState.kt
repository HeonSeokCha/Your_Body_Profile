package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class BloodSugarListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, List<Int>>>>? = null,
    val bloodSugarInfo: BloodSugarInfo? = null
)
