package com.chs.your_body_profile.presentation.screen.food

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Radio
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (selectedItems.isNotEmpty()) {
                FlowRow {
                    selectedItems.forEach {
                        Text(text = it)
                    }
                }
            }

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth(),
                query = state.searchQuery,
                onQueryChange = {
                    viewModel.updateQuery(it)
                },
                onSearch = {
                    isSearchActive = false
                    viewModel.searchFood()
                },
                active = isSearchActive,
                onActiveChange = { isSearchActive = it },
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
                    items(state.searchHistory) {
                        ItemSearchHistory(
                            title = "",
                            imageVector = Icons.Default.History
                        ) {
                            viewModel.updateQuery(it)
                            isSearchActive = false
                        }
                    }
                }
            }

            if (pagingItems != null) {
                LazyColumn (
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
                    items(state.favoriteFoodList) {

                    }

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
    }
}