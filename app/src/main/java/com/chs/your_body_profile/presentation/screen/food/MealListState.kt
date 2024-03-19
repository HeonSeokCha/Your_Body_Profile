package com.chs.your_body_profile.presentation.screen.food

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class MealListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, Int>>>? = null,
    val selectInfo: MealHistoryInfo? = null
)
