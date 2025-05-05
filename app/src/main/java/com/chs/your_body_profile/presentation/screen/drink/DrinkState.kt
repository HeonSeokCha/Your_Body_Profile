package com.chs.your_body_profile.presentation.screen.drink

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class DrinkState(
    val pagingData: Flow<PagingData<Pair<LocalDate, Int>>>? = null,
    val targetDate: LocalDate = LocalDate.now(),
    val isLoading: Boolean = true
)