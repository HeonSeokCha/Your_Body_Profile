package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.chs.your_body_profile.presentation.common.ItemConfirmDialog
import com.chs.your_body_profile.presentation.common.ItemInputButton
import com.chs.your_body_profile.presentation.common.ItemSmallDateTime
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.ItemDetailHemoglobinA1cInfo
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.ItemSimpleHemoglobinA1cInfo
import java.time.LocalDate

@Composable
fun HemoglobinA1cListScreenRoot(
    viewModel: HemoglobinA1cListViewModel,
    onInput: () -> Unit,
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HemoglobinA1cListScreen(state) { intent ->
        when (intent) {
            HemoglobinA1cListEvent.OnBack -> {
                onBack()
            }

            HemoglobinA1cListEvent.OnClickInputButton -> {
                onInput()
            }

            else -> viewModel.changeIntent(intent)
        }
    }
}

@Composable
fun HemoglobinA1cListScreen(
    state: HemoglobinA1cListState,
    onIntent: (HemoglobinA1cListEvent) -> Unit
) {
    val pagingItems = state.pagingList?.collectAsLazyPagingItems()
    LaunchedEffect(pagingItems?.loadState?.refresh) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Loading) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Error) return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        onIntent(HemoglobinA1cListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
    }

    LaunchedEffect(state.selectIdx) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        if (pagingItems[state.selectIdx]?.second == null) return@LaunchedEffect
        onIntent(HemoglobinA1cListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
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
                                ItemSmallDateTime(
                                    date = item.first,
                                    currentDate = pagingItems[state.selectIdx]?.first
                                        ?: LocalDate.now()
                                ) {
                                    onIntent(HemoglobinA1cListEvent.OnChangeSelectIdx(it))
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
                items(state.selectListInfo) { info ->
                    ItemSimpleHemoglobinA1cInfo(
                        hemoglobinA1cInfo = info,
                        onClick = {
                            onIntent(HemoglobinA1cListEvent.OnClickItem(it))
                        },
                        onLongClick = {
                            onIntent(HemoglobinA1cListEvent.OnLongClickItem(it))
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
            onIntent(HemoglobinA1cListEvent.OnClickInputButton)
        }
    }

    if (state.showRemoveDialog) {
        ItemConfirmDialog(
            title = stringResource(R.string.text_sure_delete_item),
            onClick = {
                onIntent(HemoglobinA1cListEvent.OnRemoveInfo)
            },
            onDismiss = {
                onIntent(HemoglobinA1cListEvent.OnChangeShowRemoveDialog)
            }
        )
    }

    if (state.showDetailDialog) {
        ItemDetailHemoglobinA1cInfo(
            info = state.selectInfo!!,
            onDismiss = {
                onIntent(HemoglobinA1cListEvent.OnChangeShowDetailDialog)
            }
        )
    }
}