package com.chs.your_body_profile.presentation.screen.food

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.TakenMealInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class MealListState(
    val chartList: Flow<PagingData<Pair<LocalDate, Int>>>? = null,
    val dayTakenMealList: Map<TakenMealInfo, List<FoodDetailInfo>> = mapOf()
)
