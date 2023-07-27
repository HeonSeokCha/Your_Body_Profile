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
class BodyProfileService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getFoodInfo(query: String): ResponseFoodInfo {
        return client.get(Constants.FOOD_BASE_URL) {
        }.body()
    }
}