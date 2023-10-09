package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.source.db.dao.FoodDao
import java.time.LocalDate

class FoodDayTotalCaloriePaging(
    private val foodDao: FoodDao,
) : PagingSource<Int, Pair<Long, Int>>() {
    override fun getRefreshKey(state: PagingState<Int, Pair<Long, Int>>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<Long, Int>> {
        val page: Int = params.key ?: 0
        val localResult = foodDao.getPagingDayInfo(
            time = LocalDate.now().minusDays(page.toLong()).toMillis()
        )

        val data = LocalDate.now().minusDays(page + 30L)
            .datesUntil(LocalDate.now().minusDays((page).toLong()))
            .map { date ->
                if (localResult.any { it.takenDate == date.toMillis() }) {
                    date.toMillis() to localResult.filter { it.takenDate == date.toMillis() }.map { it.calorie }.sum().toInt()
                } else {
                    date.toMillis() to 0
                }
        }.toList()

        return LoadResult.Page(
            data = data,
            prevKey = if (page == 0) null else page - 30,
            nextKey = page + 30
        )
    }
}