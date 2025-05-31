package com.chs.your_body_profile.presentation.screen.drink

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.chs.your_body_profile.presentation.common.ItemInputButton
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.ItemHemoglobinA1cInfo
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.list.HemoglobinA1cListEvent
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
        if (pagingItems?.loadState?.refresh is LoadState.Loading) return@LaunchedEffect
        if (pagingItems?.loadState?.refresh is LoadState.Error) return@LaunchedEffect
        if (pagingItems?.itemCount == 0) return@LaunchedEffect
        onIntent(DrinkListEvent.OnChangeSelectIdx(0))
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
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                reverseLayout = true
            ) {
                if (pagingItems != null) {
                    items(
                        count = pagingItems.itemCount,
                        key = pagingItems.itemKey { it.first }
                    ) {
                        val item = pagingItems[it]
                        if (item != null) {
                            Column(
                                modifier = Modifier
                                    .clickable {
                                        onIntent(DrinkListEvent.OnChangeSelectIdx(it))
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "${item.second.count()}잔")

                                Text(text = item.first.toString())
                            }
                        }
                    }
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