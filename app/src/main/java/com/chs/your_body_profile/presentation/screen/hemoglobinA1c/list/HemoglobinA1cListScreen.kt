package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.chs.your_body_profile.presentation.common.ItemInputButton
import com.chs.your_body_profile.presentation.common.ItemVerticalChart
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.ItemHemoglobinA1cInfo

@Composable
fun HemoglobinA1cListScreenRoot(
    viewModel: HemoglobinA1cListViewModel,
    onInput: () -> Unit,
    onBack: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    HemoglobinA1cListScreen(state) { intent ->
        onInput()
    }
}

@Composable
fun HemoglobinA1cListScreen(
    state: HemoglobinA1cListState,
    onIntent: (HemoglobinA1cListEvent) -> Unit
) {
    val pagingList = state.pagingList?.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ItemVerticalChart(pagingList) { }

            LazyColumn {
                item {

                }

                items(state.selectedHemoglobinA1cInfo) { info ->
                    ItemHemoglobinA1cInfo(info)
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
}