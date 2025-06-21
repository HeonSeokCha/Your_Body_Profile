package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.list

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class HemoglobinA1cListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, List<HemoglobinA1cInfo>>>>? = null,
    val selectIdx: Int = 0,
    val selectListInfo: List<HemoglobinA1cInfo> = emptyList(),
    val selectInfo: HemoglobinA1cInfo? = null,
    val showRemoveDialog: Boolean = false,
    val showDetailDialog: Boolean = false
)
