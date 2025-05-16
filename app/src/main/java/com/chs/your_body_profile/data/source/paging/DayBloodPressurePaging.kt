package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toLocalDate
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
) : PagingSource<Int, Pair<LocalDate, List<BloodPressureInfo>>>() {

    override fun getRefreshKey(state: PagingState<Int, Pair<LocalDate, List<BloodPressureInfo>>>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<LocalDate, List<BloodPressureInfo>>> {
        val page = params.key ?: 0

        val data = bloodPressureDao.getPagingDayInfoList(page).map {
            it.key.toLocalDate() to it.value.map { it.toBloodPressureInfo() }
        }

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isEmpty()) null else page + Constants.SEARCH_OFFSET
        )
    }
}