package com.chs.your_body_profile.data.source.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.mapper.toFoodInfo
import com.chs.your_body_profile.data.source.api.FoodService
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.FoodInfo

class SearchFoodPaging(
    private val service: FoodService,
    private val query: String
) : PagingSource<Int, FoodDetailInfo>() {

    override fun getRefreshKey(state: PagingState<Int, FoodDetailInfo>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FoodDetailInfo> {
        return try {
            val page: Int = params.key ?: 0
            val response = service.getSearchResultFoodInfo(
                startIdx = page.toString(),
                endIdx = (page + 10).toString(),
                query = query
            )

            Log.e("RESPONSE", response.toString())

            LoadResult.Page(
                data = response.foodService.row.map {
                    it.toFoodDetailInfo()
                },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.foodService.totalCount == 10) {
                    page + 10
                } else {
                    null
                }
            )
        } catch (e: Exception) {
            Log.e("RESPONSE", e.toString())
            LoadResult.Error(e)
        }
    }
}