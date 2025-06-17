package com.chs.your_body_profile.presentation.screen.bills

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toCommaFormat
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.presentation.common.ItemSimpleInfo

@Composable

fun ItemPaymentInfo(
    info: PaymentInfo,
    onClick: () -> Unit,
    onLongClick: (PaymentInfo) -> Unit
) {
    ItemSimpleInfo(
        title = info.title,
        measureUnit = info.subTitle ?: "",
        subTitle = info.paymentTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = onClick,
        onLongClick = { onLongClick(info) }
    ) {
        Text(
            text = "${info.amount.toCommaFormat()}${stringResource(id = R.string.text_payment_unit)}",
            fontSize = 20.sp
        )
    }
}