package com.chs.your_body_profile.data.mapper

import com.chs.your_body_profile.data.model.entity.BodyMeasureInfoEntity
import com.chs.your_body_profile.domain.model.BodyMeasureInfo

fun BodyMeasureInfoEntity.toBodyInfo(): BodyMeasureInfo {
    return BodyMeasureInfo(
        title = title,
        unit = unit,
        lastModifyTme = lastModifyTme
    )
}

fun BodyMeasureInfo.toBodyInfoEntity(): BodyMeasureInfoEntity {
    return BodyMeasureInfoEntity(
        title = title,
        unit = unit,
        lastModifyTme = lastModifyTme
    )
}