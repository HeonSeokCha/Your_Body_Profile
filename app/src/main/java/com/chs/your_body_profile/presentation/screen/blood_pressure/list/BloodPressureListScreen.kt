package com.chs.your_body_profile.presentation.screen.blood_pressure.list

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.chs.your_body_profile.R
import com.chs.your_body_profile.presentation.common.ItemInputButton
import com.chs.your_body_profile.presentation.screen.blood_pressure.ItemBloodPressureSummaryInfo
import com.chs.your_body_profile.presentation.screen.blood_pressure.ItemDetailBloodPressureInfo
import com.chs.your_body_profile.presentation.screen.blood_pressure.ItemSimpleBloodPressureInfo

@Composable
fun BloodPressureListScreenRoot(
    viewModel: BloodPressureListViewModel,
    onInput: () -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BloodPressureListScreen(state) { intent ->
        when (intent) {
            BloodPressureListEvent.OnBack -> {
                onBack()
            }

            BloodPressureListEvent.OnClickInputButton -> {
                onInput()
            }

            else -> viewModel.changeIntent(intent)
        }
    }
}

@Composable
fun BloodPressureListScreen(
    state: BloodPressureListState,
    onIntent: (BloodPressureListEvent) -> Unit
) {
    val pagingItems = state.pagingList?.collectAsLazyPagingItems()

    LaunchedEffect(pagingItems?.loadState?.refresh) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Loading) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Error)  return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        onIntent(BloodPressureListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
    }

    LaunchedEffect(state.selectIdx) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        if (pagingItems[state.selectIdx]?.second == null) return@LaunchedEffect
        onIntent(BloodPressureListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
                                            onIntent(BloodPressureListEvent.OnChangeSelectIdx(it))
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = "${item.second.maxOf { it.diastolic }} ~ ${item.second.maxOf { it.systolic }}")

                                    Text(text = item.first.toString())
                                }
                            }
                        }
                    }
                }
            }

            if (state.selectListInfo.isEmpty()) {
                item {
                    Text(text = stringResource(R.string.text_no_items))
                }
            } else {
                item {
                    ItemBloodPressureSummaryInfo(state.selectListInfo)
                }

                items(state.selectListInfo) { info ->
                    ItemSimpleBloodPressureInfo(
                        bloodPressureInfo = info,
                        onClick = {
                            onIntent(BloodPressureListEvent.OnClickItem(it))
                        },
                        onLongClick = {
                            onIntent(BloodPressureListEvent.OnLongClickItem(it))
                        }
                    )
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
            onIntent(BloodPressureListEvent.OnClickInputButton)
        }

        if (state.showRemoveDialog) {
            ItemInputButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MaterialTheme.colorScheme.primary),
            ) {
                onIntent(BloodPressureListEvent.OnClickInputButton)
            }
        }

        if (state.showDetailDialog) {
            ItemDetailBloodPressureInfo(
                bloodPressureInfo = state.selectInfo!!,
                onDismiss = {
                    onIntent(BloodPressureListEvent.OnChangeShowDetailDialog)
                }
            )
        }
    }
}