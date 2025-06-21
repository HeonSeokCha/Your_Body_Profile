package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo
import com.chs.your_body_profile.presentation.common.ItemSimpleInfo

@Composable
fun ItemSimpleHemoglobinA1cInfo(
    hemoglobinA1cInfo: HemoglobinA1cInfo,
    onClick: (HemoglobinA1cInfo) -> Unit,
    onLongClick: (HemoglobinA1cInfo) -> Unit
) {
    ItemSimpleInfo(
        title = hemoglobinA1cInfo.number.toString(),
        measureUnit = stringResource(id = R.string.text_hemoglobin_A1c_unit),
        subTitle = hemoglobinA1cInfo.measureDate.format(Constants.DATE_TIME_FORMATTER_DETAIL),
        onClick = { onClick(hemoglobinA1cInfo) },
        onLongClick = { onLongClick(hemoglobinA1cInfo) }
    ) { }
}

@Composable
fun ItemDetailHemoglobinA1cInfo(
    info: HemoglobinA1cInfo,
    onDismiss: () -> Unit
) {
    ItemDetailInfo(
        dateTime = info.measureDate,
        subComposable = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${info.number} ${stringResource(R.string.text_hemoglobin_A1c_unit)}")

                if (info.memo != null) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = info.memo)
                }
            }
        },
        onDismiss = onDismiss
    )
}