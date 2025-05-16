package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toWeightInfo
import com.chs.your_body_profile.data.source.db.dao.WeightInfoDao
import com.chs.your_body_profile.domain.model.WeightInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import kotlin.math.roundToInt

class DayWeightPaging(
    private val weightInfoDao: WeightInfoDao
) : PagingSource<Int, Pair<LocalDate, List<WeightInfo>>>() {

    override fun getRefreshKey(state: PagingState<Int, Pair<LocalDate, List<WeightInfo>>>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<LocalDate, List<WeightInfo>>> {
        val page = params.key ?: 0

        val data = weightInfoDao
            .getPagingDayInfoList(page)
            .map {
                it.key.toLocalDate() to it.value.map { it.toWeightInfo() }
            }
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isEmpty()) null else page + Constants.SEARCH_OFFSET
        )
    }
}