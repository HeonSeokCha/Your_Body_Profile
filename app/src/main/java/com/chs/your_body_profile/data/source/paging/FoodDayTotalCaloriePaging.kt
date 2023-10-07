package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.data.source.db.dao.FoodDao

class FoodDayTotalCaloriePaging(
    private val foodDao: FoodDao
) : PagingSource<Int, Int>() {
    override fun getRefreshKey(state: PagingState<Int, Int>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Int> {
        TODO("Not yet implemented")
    }

}