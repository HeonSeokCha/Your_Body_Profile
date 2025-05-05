package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.dateUntilToList
import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.source.db.dao.DrinkDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class DayDrinkPaging(
    private val drinkDao: DrinkDao,
    private val drinkType: String
) : PagingSource<LocalDate, Pair<LocalDate, Int>>() {

    override fun getRefreshKey(state: PagingState<LocalDate, Pair<LocalDate, Int>>): LocalDate? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minusDays(1) ?: page?.nextKey?.plusDays(1)
        }
    }

    override suspend fun load(params: LoadParams<LocalDate>): LoadResult<LocalDate, Pair<LocalDate, Int>> {
        val pageDate = params.key ?: LocalDate.now()

        val data = withContext(Dispatchers.IO) {
            pageDate.minusDays(Constants.SEARCH_OFFSET.toLong())
                .dateUntilToList(pageDate)
                .run {
                    drinkDao.getDayDrinkInfoList(
                        beginDate = this.min().toMillis(),
                        endDate = this.max().toMillis(),
                        drinkType = drinkType
                    )
                }.map {
                    it.takenDate.toLocalDate() to it.totalCups
                }
        }

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = pageDate.minusDays(Constants.SEARCH_OFFSET + 1L)
        )
    }
}