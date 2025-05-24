package com.chs.your_body_profile.presentation.screen.weight.list

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.WeightInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class WeightListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, List<WeightInfo>>>>? = null,
    val selectInsulinInfoList: List<WeightInfo> = emptyList()
)