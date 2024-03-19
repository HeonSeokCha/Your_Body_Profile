package com.chs.your_body_profile.presentation.screen.insulin

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.InsulinInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class InsulinListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, Int>>>? = null,
    val insulinInfo: InsulinInfo? = null
)
