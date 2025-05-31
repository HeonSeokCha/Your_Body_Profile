package com.chs.your_body_profile.presentation.screen.drink

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.presentation.common.ItemDetailInfo

@Composable
fun ItemDrinkInfo(info: DrinkInfo) {
    ItemDetailInfo(
        title = "",
        measureUnit = "",
        subTitle = info.takenDateTime.format(Constants.DATE_TIME_FORMATTER_DETAIL)
    ) {
        if (info.drinkType == DrinkType.WATER) {
            Icon(imageVector = Icons.Filled.LocalDrink, contentDescription = null)
        } else {
            Icon(imageVector = Icons.Filled.Coffee, contentDescription = null)
        }
    }
}