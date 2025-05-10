package com.chs.your_body_profile.presentation.common.picker

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
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
fun <T> ItemScrollPicker(
    items: List<T>,
    modifier: Modifier = Modifier,
    state: SimpleItemScrollState,
    onIndexChange: (Int) -> Unit,
) {
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = state.listState)

    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = with(LocalDensity.current) { itemHeightPixels.intValue.toDp() }
    val scope = rememberCoroutineScope()
    var isEditAble by remember { mutableStateOf(false) }

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
            if (isEditAble) {
                item {
                    ItemScrollEditText(
                        value = items[state.currentIndex].toString(),
                        onValueChange = {
                            state.currentIndex = items.indexOf(it as T)
                            scope.launch {
                                state.scrollToItem(items.indexOf(it as T))
                            }
                        },
                        predicate = { it as T in items },
                        onBack = {
                            isEditAble = false
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )
                }
            } else {
                items(state.listSize + state.visibleItemsCount - 1) { idx ->
                    if (idx < state.visibleItemsCount / 2 || idx >= state.listSize + state.visibleItemsCount / 2) {
                        Spacer(modifier = Modifier.height(itemHeightDp))
                    } else {
                        Box(
                            modifier = Modifier
                                .onSizeChanged { size ->
                                    itemHeightPixels.intValue = size.height
                                }
                                .padding(vertical = 20.dp / 2)
                        ) {
                            Text(
                                text = items[idx - state.visibleItemsCount / 2].toString(),
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
}

data class SimpleItemScrollState(
    val itemAmount: Int,
    val initialIndex: Int = 0,
    val visibleItemsCount: Int
) {
    val listSize = itemAmount

    val listState: LazyListState = LazyListState(firstVisibleItemIndex = 0)

    var currentIndex by mutableIntStateOf(initialIndex)

    val isScrolling: Boolean
        get() = listState.isScrollInProgress

    suspend fun scrollToItem(index: Int) =
        listState.scrollToItem(index)
}