package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toBloodPressureInfo
import com.chs.your_body_profile.data.source.db.dao.BloodPressureDao
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import kotlin.math.roundToInt

class DayBloodPressurePaging(
    private val bloodPressureDao: BloodPressureDao
) : PagingSource<LocalDate, Pair<LocalDate, Pair<Int, Int>>>(){

    override fun getRefreshKey(state: PagingState<LocalDate, Pair<LocalDate, Pair<Int, Int>>>): LocalDate? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minusDays(1) ?: page?.nextKey?.plusDays(1)
        }
    }

    override suspend fun load(params: LoadParams<LocalDate>): LoadResult<LocalDate, Pair<LocalDate, Pair<Int, Int>>> {
        val pageDate = params.key ?: LocalDate.now()

        val data = withContext(Dispatchers.IO) {
            pageDate.minusDays(Constants.SEARCH_OFFSET.toLong())
                .datesUntil(pageDate.plusDays(1L))
                .toList()
                .reversed()
                .map {
                    it to bloodPressureDao.getDayInfoList(it.toMillis()).run {
                        this.map { it.systolic }.average().roundToInt() to
                                this.map { it.diastolic }.average().roundToInt()
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