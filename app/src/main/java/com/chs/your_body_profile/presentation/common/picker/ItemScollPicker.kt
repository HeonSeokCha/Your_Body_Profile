package com.chs.your_body_profile.presentation.common.picker

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun ItemScrollPicker(
    modifier: Modifier = Modifier,
    state: SimpleItemScrollState,
    onIndexChange: (Int) -> Unit,
    item: @Composable (index: Int) -> Unit,
    activeItem: @Composable (index: Int) -> Unit
) {
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = state.listState)

    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = with(LocalDensity.current) { itemHeightPixels.intValue.toDp() }

    LaunchedEffect(state.listState) {
        snapshotFlow { state.listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                onIndexChange(index)
            }
    }

    Box(modifier = modifier) {

        LazyColumn(
            state = state.listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            reverseLayout = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeightDp * state.visibleItemsCount)
        ) {
            items(state.listSize + state.visibleItemsCount - 1) { index ->
                if (index < state.visibleItemsCount / 2 || index >= state.listSize + state.visibleItemsCount / 2) {
                    Spacer(modifier = Modifier.height(itemHeightDp))
                } else {
                    Box(
                        modifier = Modifier
                            .onSizeChanged { size ->
                                itemHeightPixels.intValue = size.height
                            }
                            .padding(
                                vertical = 20.dp / 2
                            )
                    ) {
                        item(index - state.visibleItemsCount / 2)
//                        if (index == state.currentIndex) {
//                            activeItem(index - state.visibleItemsCount / 2)
//                        } else {
//                            item(index - state.visibleItemsCount / 2)
//                        }
                    }
                }
            }
        }
    }
}

data class SimpleItemScrollState(
    val itemAmount: Int,
    val initialIndex: Int = 0,
    val visibleItemsCount: Int
) {
    val listSize = itemAmount

    val listState: LazyListState = LazyListState(
        firstVisibleItemIndex = 0
    )

    var currentIndex by mutableIntStateOf(initialIndex)

    val isScrolling: Boolean
        get() = listState.isScrollInProgress

    suspend fun scrollToItem(index: Int) =
        listState.scrollToItem(index)
}