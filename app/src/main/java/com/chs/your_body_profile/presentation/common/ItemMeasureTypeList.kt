package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemMeasureTypeList(
    title: String,
    items: List<String>,
    onClick: (String) -> Unit
) {
    ItemTitleCard(title = title) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(items) {
                Column (
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { onClick(it) }) {
                        Icon(
                            Icons.Rounded.Fastfood,
                            modifier = Modifier
                                .size(48.dp),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = it,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewItemMeasureTypeList() {
    ItemMeasureTypeList(
        title = "현재 상태",
        items = listOf(
            "공복",
            "식전",
            "식후",
            "취침 전",
            "평상시"
        )
    ) {

    }
}