package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.data.mapper.toPaymentInfo
import com.chs.your_body_profile.data.source.db.dao.PayInfoDao
import com.chs.your_body_profile.domain.model.PaymentInfo
import java.time.LocalDate

class DayPayInfoPaging(
   private val payInfoDao: PayInfoDao
) : PagingSource<Int, Pair<LocalDate, List<PaymentInfo>>>() {
    override fun getRefreshKey(state: PagingState<Int, Pair<LocalDate, List<PaymentInfo>>>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<LocalDate, List<PaymentInfo>>> {
        val page = params.key ?: 0

        val data = payInfoDao
            .getPagingDayInfoList(page)
            .map {
                it.key.toLocalDate() to it.value.map { it.toPaymentInfo() }
            }
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isEmpty()) null else page + Constants.SEARCH_OFFSET
        )
    }
}