package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemMeasureTypeList(
    title: String,
    items: List<String>,
    onClick: (String) -> Unit
) {
    Card {
        Text(text = title)
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) {
                Column {
                    IconButton(onClick = { onClick(it) }) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = it)
                }
            }
        }
    }
}