package com.chs.your_body_profile.presentation.screen.drink

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.presentation.common.ItemSimpleInfo

@Composable
fun ItemDrinkInfo(
    info: DrinkInfo,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    ItemSimpleInfo(
        title = (if (info.drinkType == DrinkType.WATER) {
            "물"
        } else "커피").run { "$this 1"},
        measureUnit = stringResource(R.string.text_drink_unit),
        subTitle = info.takenDateTime.format(Constants.DATE_TIME_FORMATTER_DETAIL),
        onClick = onClick,
        onLongClick = onLongClick
    ) {
        if (info.drinkType == DrinkType.WATER) {
            Icon(imageVector = Icons.Filled.LocalDrink, contentDescription = null)
        } else {
            Icon(imageVector = Icons.Filled.Coffee, contentDescription = null)
        }
    }
}