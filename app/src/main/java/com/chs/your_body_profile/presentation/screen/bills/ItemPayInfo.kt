package com.chs.your_body_profile.presentation.screen.bills

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo

@Composable
fun ItemPaymentInfo(info: PaymentInfo) {
    ItemDetailInfo(
        title = String.format("%,d", info.amount),
        measureUnit = stringResource(id = R.string.text_payment_unit),
        subTitle = info.paymentTime.format(Constants.DATE_TIME_FORMATTER)
    ) {
        Icon(imageVector = Icons.Filled.WbSunny, contentDescription = null)
    }
}