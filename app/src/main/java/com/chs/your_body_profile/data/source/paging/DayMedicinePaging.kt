package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toMedicineInfo
import com.chs.your_body_profile.data.source.db.dao.MedicineDao
import com.chs.your_body_profile.domain.model.MedicineInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class DayMedicinePaging(
    private val medicineDao: MedicineDao
) : PagingSource<LocalDate, Pair<LocalDate, List<MedicineInfo>>>() {

    override fun getRefreshKey(state: PagingState<LocalDate, Pair<LocalDate, List<MedicineInfo>>>): LocalDate? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minusDays(1) ?: page?.nextKey?.plusDays(1)
        }
    }

    override suspend fun load(params: LoadParams<LocalDate>): LoadResult<LocalDate, Pair<LocalDate, List<MedicineInfo>>> {
        val pageDate = params.key ?: LocalDate.now()

        val data = withContext(Dispatchers.IO) {
            pageDate.minusDays(Constants.SEARCH_OFFSET.toLong())
                .datesUntil(pageDate.plusDays(1L))
                .toList()
                .reversed()
                .map {
                    it to medicineDao.getDayInfoList(it.toMillis()).map {
                        it.toMedicineInfo()
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