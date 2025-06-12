package com.chs.your_body_profile.presentation.screen.drink

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.chs.your_body_profile.presentation.common.ItemVerticalChart
import kotlin.collections.isNotEmpty

@Composable
fun DrinkListScreenRoot(
    viewModel: DrinkListViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DrinkListScreen(state = state) { event ->
        when (event) {
            DrinkListEvent.OnBack -> onBack()
            DrinkListEvent.OnLongClickItem -> {}
            else -> viewModel.changeEvent(event)
        }
    }
}

@Composable
fun DrinkListScreen(
    state: DrinkListState,
    onIntent: (DrinkListEvent) -> Unit
) {
    val pagingItems = state.pagingData?.collectAsLazyPagingItems()
    LaunchedEffect(pagingItems?.loadState?.refresh) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Loading) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Error) return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        onIntent(DrinkListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
    }

    LaunchedEffect(state.selectIdx) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        if (pagingItems[state.selectIdx] == null) return@LaunchedEffect
        onIntent(DrinkListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            if (pagingItems != null) {
                ItemVerticalChart(
                    pagingItems = pagingItems,
                    selectIdx = state.selectIdx
                ) {
                    onIntent(DrinkListEvent.OnChangeSelectIdx(it))
                }
            }
        }

        if (state.selectInfo.isNotEmpty()) {
            items(state.selectInfo) {
                ItemDrinkInfo(it)
            }
        }
    }
}