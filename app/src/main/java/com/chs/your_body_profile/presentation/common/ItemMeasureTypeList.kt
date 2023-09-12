package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.domain.model.MeasureType

@Composable
fun ItemMeasureTypeHorizontalList(
    title: String,
    items: List<String>,
    onClick: (String) -> Unit
) {
    var selectIdx by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        onClick(items[selectIdx])
    }

    ItemTitleCard(title = title) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(items.size) {
                Column (
                    modifier = Modifier
                        .clickable { selectIdx = it },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { onClick(items[it]) }) {
                        Icon(
                            Icons.Rounded.Fastfood,
                            modifier = Modifier
                                .size(48.dp),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = items[it],
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ItemMeasureTypeVerticalList(
    title: String,
    items: List<String>,
    onClick: (String) -> Unit
) {
    var selectIdx by remember { mutableIntStateOf(0) }

    ItemTitleCard(title = title) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
        ) {
            items(items.size) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectIdx = it
                            onClick(items[it])
                       },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = items[it],
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}