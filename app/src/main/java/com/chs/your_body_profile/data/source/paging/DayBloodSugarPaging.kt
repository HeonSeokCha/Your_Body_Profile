package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.source.db.dao.BloodSugarDao
import java.time.LocalDate

class DayBloodSugarPaging(
    private val bloodSugarDao: BloodSugarDao
)  : PagingSource<Long, Pair<LocalDate, Int>>() {
    override fun getRefreshKey(state: PagingState<Long, Pair<LocalDate, Int>>): Long? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1L) ?: page?.nextKey?.plus(1L)
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Pair<LocalDate, Int>> {

        val page: Long = params.key ?: 0L
        val localDate: LocalDate = LocalDate.now()
        val localResult = bloodSugarDao.getPagingDayInfo(
            startDate = localDate.minusDays(page + 30L).toMillis(),
            endDate = localDate.minusDays(page).toMillis()
        )

        val data = localDate.minusDays(page + 30L)
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
            prevKey = if (page == 0L) null else page - 30,
            nextKey = page + 30
        )
    }
}