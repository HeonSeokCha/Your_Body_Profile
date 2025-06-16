package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo

@Composable
fun ItemBloodSugarInfo(
    bloodSugarInfo: BloodSugarInfo,
    onClick: () -> Unit,
    onLongClick: (BloodSugarInfo) -> Unit
) {
    ItemDetailInfo(
        title = bloodSugarInfo.number.toString(),
        measureUnit = stringResource(id = R.string.text_blood_sugar_unit),
        subTitle = bloodSugarInfo.measureDateTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = onClick,
        onLongClick = { onLongClick(bloodSugarInfo) }
    ) {
        val measureInfo = Constants.bloodSugarMeasureList[bloodSugarInfo.measureTypeIdx]
        Row {
            Text(text = measureInfo.first)
            Icon(imageVector = measureInfo.second, contentDescription = null)
        }
    }
}