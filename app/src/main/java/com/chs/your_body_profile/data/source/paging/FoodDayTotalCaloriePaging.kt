package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.source.db.dao.FoodDao
import java.time.LocalDate

class FoodDayTotalCaloriePaging(
    private val foodDao: FoodDao,
) : PagingSource<Long, Pair<Long, Int>>() {
    override fun getRefreshKey(state: PagingState<Long, Pair<Long, Int>>): Long? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1L) ?: page?.nextKey?.plus(1L)
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Pair<Long, Int>> {
        val page: Long = params.key ?: 0L
        val localDate: LocalDate = LocalDate.now()
        val localResult = foodDao.getPagingDayInfo(
            startDate = localDate.minusDays(page + 30L).toMillis(),
            endDate = localDate.minusDays(page).toMillis()
        )

        val data = LocalDate.now().minusDays(page + 30L)
            .datesUntil(LocalDate.now().plusDays(1L).minusDays(page))
            .map { date ->
                if (localResult.any { it.takenDate == date.toMillis() }) {
                    date.toMillis() to localResult.filter { it.takenDate == date.toMillis() }.map { it.calorie }.sum().toInt()
                } else {
                    date.toMillis() to 0
                }
        }.toList().sortedByDescending { it.first }

        return LoadResult.Page(
            data = data,
            prevKey = if (page == 0L) null else page - 30,
            nextKey = page + 30
        )
    }
}