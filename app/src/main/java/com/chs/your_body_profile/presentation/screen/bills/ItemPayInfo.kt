package com.chs.your_body_profile.presentation.screen.bills

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toCommaFormat
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo

@Composable

fun ItemPaymentInfo(
    info: PaymentInfo,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    ItemDetailInfo(
        title = info.amount.toCommaFormat(),
        measureUnit = stringResource(id = R.string.text_payment_unit),
        subTitle = info.paymentTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = onClick,
        onLongClick = onLongClick
    ) {
        Text(
            text = info.title,
            fontSize = 20.sp
        )
    }
}