package com.chs.your_body_profile.presentation.screen.insulin.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.chs.your_body_profile.presentation.common.ItemInputButton

@Composable
fun InsulinListScreenRoot(
    viewModel: InsulinListViewModel,
    onInput: () -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    InsulinListScreen(state) {

    }
}

@Composable
fun InsulinListScreen(
    state: InsulinListState,
    onIntent: (InsulinListEvent) -> Unit
) {
    val pagingItems = state.pagingList?.collectAsLazyPagingItems()

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
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
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
                            Text(text = item.first.toString())
                        }
                    }
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
            onIntent(InsulinListEvent.OnClickInputButton)
        }
    }
}
