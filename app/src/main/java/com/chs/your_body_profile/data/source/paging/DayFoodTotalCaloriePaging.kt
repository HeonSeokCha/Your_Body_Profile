package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import java.time.LocalDate

class DayFoodTotalCaloriePaging(
    private val mealHistoryWithFoodDao: MealHistoryWithFoodDao
) : PagingSource<LocalDate, Pair<LocalDate, Int>>() {
    override fun getRefreshKey(state: PagingState<LocalDate, Pair<LocalDate, Int>>): LocalDate? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minusDays(1) ?: page?.nextKey?.plusDays(1)
        }
    }

    override suspend fun load(params: LoadParams<LocalDate>): LoadResult<LocalDate, Pair<LocalDate, Int>> {
        val pageDate: LocalDate = params.key ?: LocalDate.now()
        val localResult = mealHistoryWithFoodDao.getPagingDayInfo(
            startDate = pageDate.minusDays(Constants.SEARCH_OFFSET.toLong()).toMillis(),
            endDate = pageDate.toMillis()
        )

        val data = pageDate.minusDays(Constants.SEARCH_OFFSET.toLong())
            .datesUntil(pageDate.plusDays(1L))
            .map {
                if (localResult.containsKey(it.toMillis())) {
                    it to localResult[it.toMillis()]!!.toInt()
                } else {
                    it to 0
                }
        }.toList().sortedByDescending { it.first }

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = pageDate.minusDays(Constants.SEARCH_OFFSET + 1L)
        )
    }
}