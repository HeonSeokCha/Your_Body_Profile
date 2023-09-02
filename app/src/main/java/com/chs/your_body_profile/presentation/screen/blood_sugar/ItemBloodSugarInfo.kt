package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo

@Composable
fun ItemBloodSugarInfo(
    bloodSugarInfo: BloodSugarInfo,
) {
    ItemDetailInfo(
        title = bloodSugarInfo.number.toString(),
        measureUnit = stringResource(id = R.string.text_blood_sugar_unit),
        subTitle = bloodSugarInfo.measureTime.format(Constants.DATE_TIME_FORMATTER)
    ) {
        Icon(imageVector = Icons.Filled.WbSunny, contentDescription = null)
    }
}