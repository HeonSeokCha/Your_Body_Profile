package com.chs.your_body_profile.presentation.screen.food

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.ItemSearchHistory

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FoodSearchScreen(
    mealType: String,
    navController: NavHostController,
    viewModel: FoodSearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val pagingItems = state.searchResult?.collectAsLazyPagingItems()
    val selectedItems = remember {
        mutableStateListOf<String>()
    }

    LaunchedEffect(context, viewModel) {
        viewModel.getRecentFoodSearchHistory()
    }

    var isSearchActive by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            FoodAppBar(
                navController = navController,
                mealType = mealType,
                selectCount = selectedItems.size
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (selectedItems.isNotEmpty()) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(
                                vertical = 4.dp,
                                horizontal = 8.dp
                            ),
                    ) {
                        selectedItems.forEach { foodName ->
                            ItemSelectFood(
                                name = foodName,
                                onClick = { selectedFoodName ->
                                    viewModel.removeItem(selectedFoodName)
                                }
                            )
                        }
                    }
                }

                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    query = state.searchQuery,
                    onQueryChange = { query ->
                        viewModel.updateQuery(query)
                    },
                    onSearch = { query ->
                        isSearchActive = false
                        viewModel.upsertFoodSearchHistory(query)
                        viewModel.searchFood()
                    },
                    active = isSearchActive,
                    onActiveChange = { query -> isSearchActive = query },
                    placeholder = { Text("Search here...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = {
                            if (state.searchQuery.isNotEmpty()) {
                                viewModel.updateQuery("")
                            } else {
                                isSearchActive = false
                            }
                        }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(it)
                    ) {
                        items(state.searchHistory) {query ->
                            ItemSearchHistory(
                                title = query,
                                imageVector = Icons.Default.History
                            ) { query ->
                                viewModel.updateQuery(query)
                                viewModel.upsertFoodSearchHistory(query)
                                viewModel.searchFood()
                                isSearchActive = false
                            }
                        }
                    }
                }

                if (pagingItems != null) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        items(pagingItems.itemCount) { idx ->
                            val item = pagingItems[idx]
                            if (item != null) {
                                val isSelected = selectedItems.contains(item.name)
                                ItemSearchFoodInfo(
                                    info = item,
                                    onClick = {
                                        if (isSelected) {
                                            selectedItems.remove(it.name)
                                        } else {
                                            selectedItems.add(it.name)
                                        }
                                    }, leadingContent = {
                                        if (isSelected) {
                                            Icon(
                                                imageVector = Icons.Rounded.CheckCircle,
                                                contentDescription = null
                                            )
                                        } else {
                                            Icon(
                                                imageVector = Icons.Rounded.RadioButtonUnchecked,
                                                contentDescription = null
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                } else {
                    LazyColumn {
                        items(state.recentFoodList) {

                        }
                    }
                }

                when (pagingItems?.loadState?.source?.append) {
                    is LoadState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is LoadState.Error -> {
                        Toast.makeText(
                            context,
                            "An error occurred while loading...",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                    else -> {}
                }

                when (pagingItems?.loadState?.source?.refresh) {
                    is LoadState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is LoadState.Error -> {
                        Toast.makeText(
                            context,
                            "An error occurred while loading...",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                    else -> {}
                }
            }
            if (selectedItems.isNotEmpty()) {
                ItemInputBottomMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .align(Alignment.BottomCenter)
                        .background(MaterialTheme.colorScheme.primary),
                    onClick = {
                    },
                    onDismiss = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}