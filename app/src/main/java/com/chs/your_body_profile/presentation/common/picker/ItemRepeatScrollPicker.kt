package com.chs.your_body_profile.presentation.common.picker

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun ItemRepeatScrollPicker(
    modifier: Modifier = Modifier,
    state: RepeatingItemScrollState,
    onIndexChange: (Int) -> Unit,
    item: @Composable (index: Int) -> Unit,
    activeItem: @Composable (index: Int) -> Unit
) {
    fun normalizeIndex(index: Int): Int = index % state.itemAmount
    fun middleIndexForFirst(firstVisibleItemIndex: Int): Int =
        normalizeIndex(firstVisibleItemIndex + state.visibleItemsCount / 2)

    val flingBehavior = rememberSnapFlingBehavior(lazyListState = state.listState)

    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = with(LocalDensity.current) { itemHeightPixels.intValue.toDp() }
    val fadingEdgeGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to Color.Black,
            1f to Color.Transparent
        )
    }

    LaunchedEffect(state.listState) {
        snapshotFlow { state.listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect {
                onIndexChange(middleIndexForFirst(it))
            }
    }

    Box(modifier = modifier) {
        LazyColumn(
            state = state.listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeightDp * state.visibleItemsCount)
                .fadingEdge(fadingEdgeGradient),
        ) {
            items(state.listSize) { idx ->
                val index = normalizeIndex(idx)
                Box(
                    modifier = Modifier
                        .onSizeChanged { size ->
                            itemHeightPixels.intValue = size.height
                        }
                        .padding(vertical = 20.dp / 2)
                ) {
                    item(normalizeIndex(index))
//                    if (index == state.currentIndex) {
//                        activeItem(normalizeIndex(index))
//                    } else {
//                        item(normalizeIndex(index))
//                    }
                }
            }
        }
    }
}

data class RepeatingItemScrollState(
    val itemAmount: Int,
    val initialIndex: Int = 0,
    val visibleItemsCount: Int
) {
    val listSize = Int.MAX_VALUE

    val listState: LazyListState = LazyListState(
        firstVisibleItemIndex = listSize / 2 + (itemAmount - (listSize / 2 % itemAmount)) + initialIndex - visibleItemsCount / 2
    )

    var currentIndex by mutableIntStateOf(initialIndex)

    val isScrolling: Boolean
        get() = listState.isScrollInProgress

    suspend fun scrollToItem(index: Int) =
        listState.scrollToItem(index - visibleItemsCount / 2 + listSize / 2 + (itemAmount - (listSize / 2 % itemAmount)))
}

private fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }