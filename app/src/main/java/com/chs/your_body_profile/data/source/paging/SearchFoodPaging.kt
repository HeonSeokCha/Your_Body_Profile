package com.chs.your_body_profile.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chs.your_body_profile.data.mapper.toFoodInfo
import com.chs.your_body_profile.data.source.api.FoodService
import com.chs.your_body_profile.domain.model.FoodInfo

class SearchFoodPaging(
    private val service: FoodService,
    private val query: String
) : PagingSource<Int, FoodInfo>() {

    override fun getRefreshKey(state: PagingState<Int, FoodInfo>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FoodInfo> {
        return try {
            val page: Int = params.key ?: 1
            val response = service.getSearchResultFoodInfo(
                page = page,
                query = query
            ).body

            LoadResult.Page(
                data = response.items.map {
                    it.toFoodInfo()
                },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if ((response.pageNo * response.numOfRows) > response.totalCount) {
                    page + 1
                } else {
                    null
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}