package com.chs.your_body_profile.presentation.screen.blood_sugar.list

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
import com.chs.your_body_profile.presentation.common.ItemChartDate
import com.chs.your_body_profile.presentation.common.ItemInputButton
import com.chs.your_body_profile.presentation.common.ItemSmallDateTime
import com.chs.your_body_profile.presentation.screen.blood_sugar.ItemBloodSugarInfo
import com.chs.your_body_profile.presentation.screen.blood_sugar.ItemBloodSugarSummaryInfo
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun BloodSugarListScreenRoot(
    viewModel: BloodSugarListViewModel,
    onInput: () -> Unit,
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BloodSugarListScreen(state = state) { intent ->
        when (intent) {
            BloodSugarListEvent.OnBack -> {
                onBack()
            }

            BloodSugarListEvent.OnClickInputButton -> {
                onInput()
            }

            else -> viewModel.changeIntent(intent)
        }
    }
}

@Composable
fun BloodSugarListScreen(
    state: BloodSugarListState,
    onIntent: (BloodSugarListEvent) -> Unit
) {
    val pagingItems = state.pagingList?.collectAsLazyPagingItems()

    LaunchedEffect(pagingItems?.loadState?.refresh) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Loading) return@LaunchedEffect
        if (pagingItems.loadState.refresh is LoadState.Error)  return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        onIntent(BloodSugarListEvent.OnSelectInfo(pagingItems[0]!!.second))
    }

    LaunchedEffect(state.selectIdx) {
        if (pagingItems == null) return@LaunchedEffect
        if (pagingItems.itemCount == 0) return@LaunchedEffect
        if (pagingItems[state.selectIdx]?.second == null) return@LaunchedEffect
        onIntent(BloodSugarListEvent.OnSelectInfo(pagingItems[state.selectIdx]!!.second))
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
                                    currentDate = pagingItems[state.selectIdx]?.first ?: LocalDate.now()
                                ) {
                                    onIntent(BloodSugarListEvent.OnChangeSelectIdx(it))
                                }
                            }
                        }
                    }
                }
            }

            if (state.selectInfo.isNotEmpty()) {
                item {
                    ItemBloodSugarSummaryInfo(state.selectInfo)
                }

                items(state.selectInfo) { info ->
                    ItemBloodSugarInfo(
                        bloodSugarInfo = info,
                        onClick = {

                        },
                        onLongClick = {

                        }
                    )
                }
            } else {
                item {
                    Text(text = stringResource(R.string.text_no_items))
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
            onIntent(BloodSugarListEvent.OnClickInputButton)
        }
    }
}