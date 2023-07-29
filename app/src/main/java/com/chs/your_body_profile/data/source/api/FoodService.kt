package com.chs.your_body_profile.data.source.api

import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.data.model.ResponseFoodInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getSearchResultFoodInfo(
        query: String,
        page: Int
    ): ResponseFoodInfo {
        return client.get(Constants.FOOD_BASE_URL) {
            this.parameter(Constants.FOOD_PARAMETER_SERVICE_KEY, "")
            this.parameter(Constants.FOOD_PARAMETER_RESULT_NO, "10")
            this.parameter(Constants.FOOD_PARAMETER_PAGE_NO, page)
            this.parameter(Constants.FOOD_PARAMETER_type, Constants.TYPE_JSON)
        }.body()
    }
}