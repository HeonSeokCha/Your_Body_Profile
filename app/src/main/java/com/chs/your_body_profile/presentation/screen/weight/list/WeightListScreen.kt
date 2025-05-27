package com.chs.your_body_profile.presentation.screen.weight.list

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
import com.chs.your_body_profile.presentation.screen.weight.ItemWeightInfo

@Composable
fun WeightListScreenRoot(
    viewModel: WeightListViewModel,
    onInput: () -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    WeightListScreen(state) { intent ->
        when (intent) {
            WeightListEvent.OnBack -> onBack()
            WeightListEvent.OnClickInputButton -> onInput()
            else -> viewModel.changeIntent(intent)
        }
    }
}

@Composable
fun WeightListScreen(
    state: WeightListState,
    onIntent: (WeightListEvent) -> Unit
) {
    val pagingItems = state.pagingList?.collectAsLazyPagingItems()
    LaunchedEffect(pagingItems?.loadState?.refresh) {
        if (pagingItems?.loadState?.refresh is LoadState.Loading) return@LaunchedEffect
        if (pagingItems?.loadState?.refresh is LoadState.Error)  return@LaunchedEffect
        if (pagingItems?.itemCount == 0) return@LaunchedEffect
        onIntent(WeightListEvent.OnChangeSelectIdx(0))
    }

    LaunchedEffect(state.selectIdx) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        if (pagingItems[state.selectIdx]?.second == null) return@LaunchedEffect
        onIntent(WeightListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            Modifier
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
                                            onIntent(WeightListEvent.OnChangeSelectIdx(it))
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = String.format("%.1f", item.second.map { it.weight }.average()))

                                    Text(text = item.first.toString())
                                }
                            }
                        }
                    }
                }
            }

            if (state.selectInfo.isNotEmpty()) {
                items(state.selectInfo) { info ->
                    ItemWeightInfo(info)
                }
            }
        }

        ItemInputButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
        ) {
            onIntent(WeightListEvent.OnClickInputButton)
        }
    }
}