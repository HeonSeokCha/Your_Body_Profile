package com.chs.your_body_profile.presentation.screen.blood_sugar.list

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.chs.your_body_profile.presentation.common.ItemInputButton
import com.chs.your_body_profile.presentation.common.ItemVerticalChart
import kotlin.math.roundToInt

@Composable
fun BloodSugarListScreenRoot(
    viewModel: BloodSugarListViewModel,
    onInput: () -> Unit,
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BloodSugarListScreen(state = state) { intent ->
        onInput()
    }
}

@Composable
fun BloodSugarListScreen(
    state: BloodSugarListState,
    onIntent: (BloodSugarListEvent) -> Unit
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
                            Column {
                                Text(text = item.second.map { it.number }.average().roundToInt().toString())

                                Text(text = item.first.toString())
                            }
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
            onIntent(BloodSugarListEvent.OnClickInputButton)
        }
    }
}