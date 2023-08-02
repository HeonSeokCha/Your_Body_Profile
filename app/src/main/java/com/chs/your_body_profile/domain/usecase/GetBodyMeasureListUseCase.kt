package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BodyMeasureInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBodyMeasureListUseCase @Inject constructor(
    private val repository: BodyRepository
) {

    operator fun invoke(): Flow<List<BodyMeasureInfo>> {
        return repository.getBodyMeasureList()
    }
}