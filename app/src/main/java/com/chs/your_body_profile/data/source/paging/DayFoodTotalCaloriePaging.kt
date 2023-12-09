package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.source.db.dao.MealHistoryWithFoodDao
import java.time.LocalDate

class DayFoodTotalCaloriePaging(
    private val mealHistoryWithFoodDao: MealHistoryWithFoodDao
) : PagingSource<Long, Pair<LocalDate, Int>>() {
    override fun getRefreshKey(state: PagingState<Long, Pair<LocalDate, Int>>): Long? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1L) ?: page?.nextKey?.plus(1L)
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Pair<LocalDate, Int>> {
        val page: Long = params.key ?: 0L
        val localDate: LocalDate = LocalDate.now()
        val localResult = mealHistoryWithFoodDao.getPagingDayInfo(
            startDate = localDate.minusDays(page + Constants.SEARCH_OFFSET.toLong()).toMillis(),
            endDate = localDate.minusDays(page).toMillis()
        )

        val data = localDate.minusDays(page + Constants.SEARCH_OFFSET.toLong())
            .datesUntil(localDate.plusDays(1L).minusDays(page))
            .map {
                if (localResult.containsKey(it.toMillis())) {
                    it to localResult[it.toMillis()]!!.toInt()
                } else {
                    it to 0
                }
        }.toList().sortedByDescending { it.first }

        return LoadResult.Page(
            data = data,
            prevKey = if (page == 0L) null else page - Constants.SEARCH_OFFSET,
            nextKey = page + Constants.SEARCH_OFFSET
        )
    }
}