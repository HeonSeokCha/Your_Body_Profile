package com.chs.your_body_profile.presentation.screen.bills

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toCommaFormat
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo
import com.chs.your_body_profile.presentation.common.ItemSimpleInfo
import java.time.LocalDateTime

@Composable
fun ItemSimplePaymentInfo(
    info: PaymentInfo,
    onClick: (PaymentInfo) -> Unit,
    onLongClick: (PaymentInfo) -> Unit
) {
    ItemSimpleInfo(
        title = info.title,
        measureUnit = info.subTitle ?: "",
        subTitle = info.paymentTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = { onClick(info) },
        onLongClick = { onLongClick(info) }
    ) {
        Text(
            text = "${info.amount.toCommaFormat()}${stringResource(id = R.string.text_payment_unit)}",
            fontSize = 20.sp
        )
    }
}

@Composable
fun ItemDetailPaymentInfo(
    info: PaymentInfo,
    onDismiss: () -> Unit
) {
    ItemDetailInfo(
        dateTime = info.paymentTime,
        subComposable = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = info.title)

                Text(text = "₩ ${(info.amount).toCommaFormat()}")

                if (info.memo != null) {
                    Text(text = info.memo)
                }
            }
        },
        onDismiss = onDismiss
    )
}