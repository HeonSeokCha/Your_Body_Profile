package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class HemoglobinA1cListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, Int>>>? = null,
    val hemoglobinA1cInfo: HemoglobinA1cInfo? = null
)
