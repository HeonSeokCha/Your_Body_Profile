package com.chs.your_body_profile.data.source.api

import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.data.model.dto.ResponseFoodInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
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
            accept(ContentType.Text.Xml)
            this.parameter("bgn_year", "2017")
            this.parameter(Constants.FOOD_PARAMETER_SERVICE_KEY, Constants.FOOD_API_KEY)
            this.parameter(Constants.FOOD_PARAMETER_RESULT_NO, Constants.FOOD_PARAMETER_RESULT_NO_VALUE)
            this.parameter(Constants.FOOD_PARAMETER_PAGE_NO, page)
            this.parameter(Constants.FOOD_PARAMETER_type, Constants.TYPE_JSON)
            this.parameter(Constants.FOOD_PARAMETER_FOOD_NAME, query)
        }.body()
    }
}