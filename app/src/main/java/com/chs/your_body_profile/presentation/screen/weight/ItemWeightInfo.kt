package com.chs.your_body_profile.presentation.screen.weight

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
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo
import com.chs.your_body_profile.presentation.common.ItemSimpleInfo

@Composable
fun ItemSimpleWeightInfo(
    info: WeightInfo,
    onClick: (WeightInfo) -> Unit,
    onLongClick: (WeightInfo) -> Unit
) {
    ItemSimpleInfo(
        title = info.weight.toString(),
        measureUnit = stringResource(id = R.string.text_weight_unit),
        subTitle = info.measureDateTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = { onClick(info) },
        onLongClick = { onLongClick(info) }
    ) {
    }
}

@Composable
fun ItemDetailWeightInfo(
    info: WeightInfo,
    onDismiss: () -> Unit
) {
    ItemDetailInfo(
        dateTime = info.measureDateTime,
        subComposable = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${info.weight} ${stringResource(R.string.text_weight_unit)}")

                if (info.memo != null) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = info.memo)
                }
            }
        },
        onDismiss = onDismiss
    )
}