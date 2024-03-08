package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toInsulinInfo
import com.chs.your_body_profile.data.source.db.dao.InsulinDao
import com.chs.your_body_profile.domain.model.InsulinInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class DayInsulinInfoPaging(
    private val insulinDao: InsulinDao
) : PagingSource<LocalDate, Pair<LocalDate, List<InsulinInfo>>>() {
    override fun getRefreshKey(state: PagingState<LocalDate, Pair<LocalDate, List<InsulinInfo>>>): LocalDate? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minusDays(1) ?: page?.nextKey?.plusDays(1)
        }
    }

    override suspend fun load(params: LoadParams<LocalDate>): LoadResult<LocalDate, Pair<LocalDate, List<InsulinInfo>>> {
        val pageDate: LocalDate = params.key ?: LocalDate.now()

        val data = withContext(Dispatchers.IO) {
            pageDate.minusDays(Constants.SEARCH_OFFSET.toLong())
                .datesUntil(pageDate.plusDays(1L))
                .toList()
                .reversed()
                .map {
                    it to insulinDao.getDayInfoList(it.toMillis()).map {
                        it.toInsulinInfo()
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