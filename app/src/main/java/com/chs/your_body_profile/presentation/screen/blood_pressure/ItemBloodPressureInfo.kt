package com.chs.your_body_profile.presentation.screen.blood_pressure

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo

@Composable
fun ItemBloodPressureInfo(
    bloodPressureInfo: BloodPressureInfo
) {
    ItemDetailInfo(
        title = "${bloodPressureInfo.systolic}/${bloodPressureInfo.diastolic}",
        measureUnit = stringResource(id = R.string.text_blood_sugar_unit),
        subTitle = bloodPressureInfo.measureDateTime.format(Constants.DATE_TIME_FORMATTER)
    ) { }
}