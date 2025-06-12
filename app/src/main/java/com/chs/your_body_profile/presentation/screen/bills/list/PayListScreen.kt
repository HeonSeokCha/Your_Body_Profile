package com.chs.your_body_profile.presentation.screen.bills.list

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
import com.chs.your_body_profile.presentation.screen.bills.ItemPaymentInfo
import androidx.compose.runtime.getValue
import com.chs.your_body_profile.common.toCommaFormat
import com.chs.your_body_profile.presentation.common.ItemSmallDateTime
import java.time.LocalDate

@Composable
fun PayInfoListScreenRoot(
    viewModel: PayListViewModel,
    onInput: () -> Unit,
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PayInfoListScreen(state = state) { intent ->
        when (intent) {
            PayListEvent.OnBack -> {
                onBack()
            }

            PayListEvent.OnClickInputButton -> {
                onInput()
            }

            else -> viewModel.changeIntent(intent)
        }
    }
}

@Composable
fun PayInfoListScreen(
    state: PayListState,
    onIntent: (PayListEvent) -> Unit
) {
    val pagingItems = state.pagingList?.collectAsLazyPagingItems()

    LaunchedEffect(pagingItems?.loadState?.refresh) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Loading) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Error)  return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        onIntent(PayListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
    }

    LaunchedEffect(state.selectIdx) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        if (pagingItems[state.selectIdx]?.second == null) return@LaunchedEffect
        onIntent(PayListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                                ItemSmallDateTime(
                                    date = item.first,
                                    currentDate = pagingItems[state.selectIdx]?.first ?: LocalDate.now()
                                ) {
                                    onIntent(PayListEvent.OnChangeSelectIdx(it))
                                }
//                                Column(
//                                    modifier = Modifier
//                                        .clickable {
//                                            onIntent(PayListEvent.OnChangeSelectIdx(it))
//                                        },
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(text = item.second.sumOf { it.amount }.toCommaFormat())
//
//                                    Text(text = item.first.toString())
//                                }
                            }
                        }
                    }
                }
            }

            if (state.selectInfo.isEmpty()) {
                item {
                    Text(text = stringResource(R.string.text_no_items))
                }
            } else {
                items(state.selectInfo) { info ->
                    ItemPaymentInfo(info)
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
            onIntent(PayListEvent.OnClickInputButton)
        }
    }
}