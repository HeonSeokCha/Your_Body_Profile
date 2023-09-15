package com.chs.your_body_profile.data.source.api

import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.data.model.dto.ResponseFoodInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendEncodedPathSegments
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getSearchResultFoodInfo(
        query: String,
        startIdx: String,
        endIdx: String,
    ): ResponseFoodInfo {
        return client.get(Constants.FOOD_BASE_URL) {
            url {
                this.appendEncodedPathSegments(
                    Constants.FOOD_API_KEY,
                    "I2790",
                    Constants.TYPE_JSON,
                    startIdx,
                    endIdx,
                    "${Constants.FOOD_PARAMETER_FOOD_NAME}=$query"
                )
            }
        }.body()
    }
}