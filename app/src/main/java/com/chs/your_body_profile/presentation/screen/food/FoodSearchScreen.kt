package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chs.your_body_profile.presentation.common.ItemSearchHistory

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FoodSearchScreen(
    mealType: String,
    navController: NavHostController,
    viewModel: FoodSearchViewModel = hiltViewModel()
) {
    var isSearchActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = mealType)

        FlowRow {

        }

        SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            query = "",
            onQueryChange = {
                viewModel.updateQuery(it)
            },
            onSearch = {
                isSearchActive = false
            },
            active = isSearchActive,
            onActiveChange = { isSearchActive = it },
            placeholder = { Text("Search here...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = {

                }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        ) {
            LazyColumn {
                item {
                    ItemSearchHistory(
                        title = "",
                        imageVector = Icons.Default.History
                    ) {
                        it
                        isSearchActive = false
                    }
                }
            }
        }

        LazyColumn {

        }
    }
}