package com.chs.your_body_profile.presentation.common.picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun <T> Picker(
    modifier: Modifier = Modifier,
    items: List<T>,
    startIdx: Int = 0,
    onValueChange: (T) -> Unit,
    infiniteScroll: Boolean = true,
) {
    if (infiniteScroll) {
        val state = remember {
            RepeatingItemScrollState(
                itemAmount = items.size,
                initialIndex = startIdx,
                visibleItemsCount = 3
            )
        }

        ItemRepeatScrollPicker(
            modifier = modifier,
            state = state,
            items = items,
            onIndexChange = { idx ->
                onValueChange(items[idx])
                state.currentIndex = idx
            },
        )
    } else {
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
            items = items,
            onIndexChange = { index ->
                onValueChange(items[index])
                state.currentIndex = index
            }
        )
    }
}