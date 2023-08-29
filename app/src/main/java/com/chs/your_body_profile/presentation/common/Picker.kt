package com.chs.your_body_profile.presentation.common

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun Picker(
    items: List<Int>,
    state: PickerState = rememberPickerState(),
    modifier: Modifier = Modifier,
    visibleItemsCount: Int = 3,
    startIdx: Int = 0,
    textModifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val visibleItemsMiddle = visibleItemsCount / 2
    val listScrollCount = Integer.MAX_VALUE
    val listScrollMiddle = listScrollCount / 2
    val listStartIndex =
        listScrollMiddle - listScrollMiddle % items.size - visibleItemsMiddle

    fun getItem(idx: Int) = items[idx % items.size]

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex + startIdx)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = pixelsToDp(itemHeightPixels.intValue)

    val fadingEdgeGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to Color.Black,
            1f to Color.Transparent
        )
    }

    val keyBoardController = LocalSoftwareKeyboardController.current
    var editEnabled by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val requestFocus = remember { FocusRequester() }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex + visibleItemsMiddle }
            .map { idx -> getItem(idx) }
            .distinctUntilChanged()
            .collect { item ->
                state.selectedItem = item
            }
    }

    SideEffect {
        if (editEnabled) {
            requestFocus.requestFocus()
        }
    }

    BackHandler(onBack = {
        if (editEnabled) {
            editEnabled = false
        } else {
            onBack()
        }
    })

    Box {
        if (editEnabled) {
            var textState by remember {
                mutableStateOf(
                    TextFieldValue(
                        text = state.selectedItem.toString(),
                        selection = TextRange(0, state.selectedItem.toString().length)
                    )
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    modifier = modifier
                        .height((itemHeightDp) * visibleItemsCount)
                        .focusRequester(requestFocus),
                    value = textState,
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        textState = it
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyBoardController?.hide()
                            if (items.contains(textState.text.toInt())) {
                                coroutineScope.launch {
                                    listState.scrollToItem(
                                        listStartIndex + items.indexOf(textState.text.toInt())
                                    )
                                }
                            }
                            editEnabled = false
                        }
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                )
            }
        } else {
            LazyColumn(
                state = listState,
                flingBehavior = flingBehavior,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .height((itemHeightDp + 16.dp) * visibleItemsCount)
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                    .drawWithContent {
                        drawContent()
                        drawRect(brush = fadingEdgeGradient, blendMode = BlendMode.DstIn)
                    },
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listScrollCount) { idx ->
                    Text(
                        text = getItem(idx).toString(),
                        fontSize = 24.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .onSizeChanged { size -> itemHeightPixels.value = size.height }
                            .then(textModifier)
                            .clickable {
                                editEnabled = true
                            }
                    )
                }
            }
        }
    }
}


@Composable
fun rememberPickerState() = remember { PickerState() }

@Composable
private fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }

class PickerState {
    var selectedItem by mutableIntStateOf(0)
}