package com.chs.your_body_profile.presentation.screen.insulin


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo

@Composable
fun ItemInsulinInfo(
    insulinInfo: InsulinInfo,
    onClick: () -> Unit,
    onLongClick: (InsulinInfo) -> Unit
) {
    ItemDetailInfo(
        title = insulinInfo.level.toString(),
        measureUnit = stringResource(id = R.string.text_insulin_unit),
        subTitle = insulinInfo.injectDateTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = onClick,
        onLongClick = { onLongClick(insulinInfo) }
    ) {
    }
}