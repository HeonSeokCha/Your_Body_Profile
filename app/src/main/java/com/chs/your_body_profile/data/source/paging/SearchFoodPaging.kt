package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.source.api.FoodService
import com.chs.your_body_profile.domain.model.FoodDetailInfo

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
            val page: Int = params.key ?: 1
            val response = service.getSearchResultFoodInfo(
                startIdx = page.toString(),
                endIdx = (page + Constants.FOOD_SEARCH_OFFSET).toString(),
                query = query
            )

            LoadResult.Page(
                data = response.foodService.row.map {
                    it.toFoodDetailInfo()
                },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.foodService.totalCount == Constants.FOOD_SEARCH_OFFSET + 1) {
                    page + 1 + Constants.FOOD_SEARCH_OFFSET
                } else {
                    null
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}