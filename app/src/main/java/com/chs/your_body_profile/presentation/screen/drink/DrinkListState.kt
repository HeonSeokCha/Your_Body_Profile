package com.chs.your_body_profile.presentation.screen.drink

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class DrinkListState(
    val drinkType: DrinkType,
    val pagingData: Flow<PagingData<Pair<LocalDate, List<DrinkInfo>>>>? = null,
    val selectInfo: List<DrinkInfo> = emptyList(),
    val selectIdx: Int = -1,
    val isLoading: Boolean = true
)