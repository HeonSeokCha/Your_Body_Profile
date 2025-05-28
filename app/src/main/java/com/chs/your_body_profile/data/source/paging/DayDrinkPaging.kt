package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.data.mapper.toDrinkInfo
import com.chs.your_body_profile.data.source.db.dao.DrinkDao
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import java.time.LocalDate

class DayDrinkPaging(
    private val drinkDao: DrinkDao,
    private val drinkType: DrinkType
) : PagingSource<Int, Pair<LocalDate, List<DrinkInfo>>>() {

        override fun getRefreshKey(state: PagingState<Int, Pair<LocalDate, List<DrinkInfo>>>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<LocalDate, List<DrinkInfo>>> {
        val page = params.key ?: 0

        val data = drinkDao.getPagingDayInfoList(
            drinkType = drinkType.name,
            page = page
        ).map {
            it.key.toLocalDate() to it.value.map { it.toDrinkInfo() }
        }

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isEmpty()) null else page + Constants.SEARCH_OFFSET
        )
    }
}