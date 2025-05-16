package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toHemoglobinA1cInfo
import com.chs.your_body_profile.data.source.db.dao.HemoglobinA1cDao
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import kotlin.math.roundToInt

class DayHemoglobinA1cInfoPaging(
    private val hemoglobinA1cDao: HemoglobinA1cDao
) : PagingSource<Int, Pair<LocalDate, List<HemoglobinA1cInfo>>>() {
    override fun getRefreshKey(state: PagingState<Int, Pair<LocalDate, List<HemoglobinA1cInfo>>>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<LocalDate, List<HemoglobinA1cInfo>>> {
        val page = params.key ?: 0

        val data = hemoglobinA1cDao.getPagingDayInfoList(page).map {
            it.key.toLocalDate() to it.value.map { it.toHemoglobinA1cInfo() }
        }

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isEmpty()) null else page + Constants.SEARCH_OFFSET
        )
    }
}