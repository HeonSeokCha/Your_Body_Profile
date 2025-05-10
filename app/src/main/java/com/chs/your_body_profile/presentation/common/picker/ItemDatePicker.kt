package com.chs.your_body_profile.presentation.common.picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.chs.your_body_profile.common.Constants
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun ItemDatePicker(
    modifier: Modifier = Modifier,
    items: List<LocalDate>,
    startIdx: Int = 0,
    onValueChange: (LocalDate) -> Unit,
) {
    val state = remember {
        SimpleItemScrollState(
            itemAmount = items.size,
            initialIndex = startIdx,
            visibleItemsCount = 3
        )
    }

    ItemScrollPicker(
        modifier = modifier,
        state = state,
        items = items.map { it.format(Constants.DATE_FORMATTER_DETAIL) },
        onIndexChange = { index ->
            onValueChange(items[index])
            state.currentIndex = index
        }
    )
}