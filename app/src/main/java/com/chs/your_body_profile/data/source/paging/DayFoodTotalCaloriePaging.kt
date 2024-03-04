package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.mapper.toTakenMealHistoryInfo
import com.chs.your_body_profile.data.source.db.dao.MealHistoryDao
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class DayFoodTotalCaloriePaging(
    private val mealHistoryDao: MealHistoryDao
) : PagingSource<LocalDate, Pair<LocalDate, List<Pair<MealHistoryInfo, List<FoodDetailInfo>>>>>() {
    override fun getRefreshKey(state: PagingState<LocalDate, Pair<LocalDate, List<Pair<MealHistoryInfo, List<FoodDetailInfo>>>>>): LocalDate? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minusDays(1) ?: page?.nextKey?.plusDays(1)
        }
    }

    override suspend fun load(params: LoadParams<LocalDate>): LoadResult<LocalDate, Pair<LocalDate, List<Pair<MealHistoryInfo, List<FoodDetailInfo>>>>> {
        val pageDate: LocalDate = params.key ?: LocalDate.now()

        val data = withContext(Dispatchers.IO) {
            pageDate.minusDays(Constants.SEARCH_OFFSET.toLong())
                .datesUntil(pageDate.plusDays(1L))
                .toList()
                .reversed()
                .map {
                    it to mealHistoryDao.getDayMealHistoryFoodInfo(it.toMillis()).map {
                        it.key.toTakenMealHistoryInfo() to it.value.map { it.toFoodDetailInfo() }
                    }
                }
        }

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = pageDate.minusDays(Constants.SEARCH_OFFSET + 1L)
        )
    }
}