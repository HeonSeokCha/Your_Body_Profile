package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo

@Composable
fun ItemHemoglobinA1cInfo(
    hemoglobinA1cInfo: HemoglobinA1cInfo,
    onClick: () -> Unit,
    onLongClick: (HemoglobinA1cInfo) -> Unit
) {
    ItemDetailInfo(
        title = hemoglobinA1cInfo.number.toString(),
        measureUnit = stringResource(id = R.string.text_hemoglobin_A1c_unit),
        subTitle = hemoglobinA1cInfo.measureDate.format(Constants.DATE_TIME_FORMATTER_DETAIL),
        onClick = onClick,
        onLongClick = { onLongClick(hemoglobinA1cInfo) }
    ) { }
}