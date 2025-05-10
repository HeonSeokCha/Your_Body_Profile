package com.chs.your_body_profile.presentation.common.picker

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun <T> ItemRepeatScrollPicker(
    modifier: Modifier = Modifier,
    items: List<T>,
    state: RepeatingItemScrollState,
    onIndexChange: (Int) -> Unit,
) {
    fun normalizeIndex(index: Int): Int = index % state.itemAmount
    fun middleIndexForFirst(firstVisibleItemIndex: Int): Int =
        normalizeIndex(firstVisibleItemIndex + state.visibleItemsCount / 2)

    val flingBehavior = rememberSnapFlingBehavior(lazyListState = state.listState)
    val scope = rememberCoroutineScope()
    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = with(LocalDensity.current) { itemHeightPixels.intValue.toDp() }
    val fadingEdgeGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to Color.Black,
            1f to Color.Transparent
        )
    }

    var isEditAble by remember { mutableStateOf(false) }

    LaunchedEffect(state.listState) {
        snapshotFlow { state.listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect {
                onIndexChange(middleIndexForFirst(it))
            }
    }

    Box(modifier = modifier) {
        if (isEditAble) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeightDp * state.visibleItemsCount)
            ) {
                ItemScrollEditText(
                    modifier = Modifier
                        .align(Alignment.Center),
                    value = items[normalizeIndex(state.currentIndex)].toString(),
                    onValueChange = {
                        state.currentIndex = items.indexOf(it as T)
                        scope.launch {
                            state.scrollToItem(items.indexOf(it as T))
                        }
                    },
                    predicate = { it as T in items },
                    onBack = { isEditAble = false },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )
            }
        } else {
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
                    Box(
                        modifier = Modifier
                            .onSizeChanged { size ->
                                itemHeightPixels.intValue = size.height
                            }
                            .clickable {
                                if (normalizeIndex(idx) == state.currentIndex) {
                                    isEditAble = true
                                }
                            }
                            .padding(vertical = 20.dp / 2)
                    ) {
                        Text(
                            text = items[normalizeIndex(idx)].toString(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
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