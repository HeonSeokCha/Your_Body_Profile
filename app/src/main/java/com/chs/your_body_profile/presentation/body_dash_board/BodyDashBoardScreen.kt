package com.chs.your_body_profile.presentation.body_dash_board

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BodyDashBoardScreen(
    viewModel: BodyDashBoardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
        }
        item {

        }
        item {

        }
        item {

        }



        item(span = StaggeredGridItemSpan.FullLine) {

        }
        item(span = StaggeredGridItemSpan.FullLine) {

        }
        item(span = StaggeredGridItemSpan.FullLine) {

        }
        item(span = StaggeredGridItemSpan.FullLine) {

        }

    }
}