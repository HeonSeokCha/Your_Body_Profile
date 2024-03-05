package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toLocalDateTime
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.source.db.dao.MealHistoryDao
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class DayFoodTotalCaloriePaging(
    private val mealHistoryDao: MealHistoryDao
) : PagingSource<LocalDate, Pair<LocalDate, List<MealHistoryInfo>>>() {
    override fun getRefreshKey(state: PagingState<LocalDate, Pair<LocalDate, List<MealHistoryInfo>>>): LocalDate? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minusDays(1) ?: page?.nextKey?.plusDays(1)
        }
    }

    override suspend fun load(params: LoadParams<LocalDate>): LoadResult<LocalDate, Pair<LocalDate, List<MealHistoryInfo>>> {
        val pageDate: LocalDate = params.key ?: LocalDate.now()

        val data = withContext(Dispatchers.IO) {
            pageDate.minusDays(Constants.SEARCH_OFFSET.toLong())
                .datesUntil(pageDate.plusDays(1L))
                .toList()
                .reversed()
                .map {
                    it to mealHistoryDao.getDayMealHistoryFoodInfo(it.toMillis()).map {

                        val mealHistoryInfo = it.key
                        MealHistoryInfo(
                            takenDateTime = mealHistoryInfo.insertTime.toLocalDateTime(),
                            foodList = it.value.map { it.toFoodDetailInfo() },
                            mealType = MealType.entries.find { it.mean.first == mealHistoryInfo.takenMealType } ?: MealType.MORNING
                        )
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