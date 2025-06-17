package com.chs.your_body_profile.presentation.screen.weight

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.presentation.common.ItemSimpleInfo

@Composable
fun ItemWeightInfo(
    info: WeightInfo,
    onClick: () -> Unit,
    onLongClick: (WeightInfo) -> Unit
) {
    ItemSimpleInfo(
        title = info.weight.toString(),
        measureUnit = stringResource(id = R.string.text_weight_unit),
        subTitle = info.measureDateTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = onClick,
        onLongClick = { onLongClick(info) }
    ) {
    }
}