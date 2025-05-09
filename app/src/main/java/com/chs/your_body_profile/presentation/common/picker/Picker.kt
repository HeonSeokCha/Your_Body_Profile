package com.chs.your_body_profile.presentation.common.picker

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.launch

@Composable
fun <T> Picker(
    modifier: Modifier = Modifier,
    items: List<T>,
    startIdx: Int = 0,
    onValueChange: (T) -> Unit,
    infiniteScroll: Boolean = true,
) {
    val scope = rememberCoroutineScope()
    if (infiniteScroll) {
        val state = remember {
            RepeatingItemScrollState(
                itemAmount = items.size,
                initialIndex = startIdx,
                visibleItemsCount = 3
            )
        }
        val item = @Composable { index: Int ->
            Text(
                text = items[index].toString(),
                textAlign = TextAlign.Center
            )
        }

        val activeItem = @Composable { idx: Int ->
            ItemScrollEditText(
                value = items[idx].toString(),
                onValueChange = {
                    onValueChange(it as T)
                    state.currentIndex = items.indexOf(it as T)
                    scope.launch {
                        state.scrollToItem(items.indexOf(it as T))
                    }
                },
                predicate = {
                    it as T in items
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }

        ItemRepeatScrollPicker(
            modifier = modifier,
            state = state,
            onIndexChange = { idx ->
                onValueChange(items[idx])
                state.currentIndex = idx
            },
            item = item,
            activeItem = activeItem
        )
    } else {
        val state = remember {
            SimpleItemScrollState(
                itemAmount = items.size,
                initialIndex = startIdx,
                visibleItemsCount = 3
            )
        }

        val item = @Composable { idx: Int ->
            Text(
                text = items[idx].toString(),
                textAlign = TextAlign.Center
            )
        }

        val activeItem = @Composable { idx: Int ->
            ItemScrollEditText(
                value = items[idx].toString(),
                onValueChange = {
                    onValueChange(it as T)
                    state.currentIndex = items.indexOf(it)
                    scope.launch {
                        state.scrollToItem(items.indexOf(it))
                    }
                },
                predicate = {
                    it as T in items
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }

        ItemScrollPicker(
            modifier = modifier,
            state = state,
            onIndexChange = { index ->
                onValueChange(items[index])
                state.currentIndex = index
            },
            item = item,
            activeItem = activeItem
        )
    }
}