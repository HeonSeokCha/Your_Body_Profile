package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastCbrt
import com.chs.your_body_profile.domain.model.MeasureType

@Composable
fun ItemMeasureTypeHorizontalList(
    title: String,
    items: List<Pair<String, ImageVector>>,
    selectedIdx: Int,
    onClick: (Int) -> Unit
) {
    ItemTitleCard(title = title) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(items.size) {
                Column(
                    modifier = Modifier
                        .background(
                            if (selectedIdx == it) {
                                MaterialTheme.colorScheme.primary
                            } else Color.Transparent
                        )
                        .clickable { onClick(it) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = items[it].second,
                        modifier = Modifier
                            .size(48.dp),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = items[it].first,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemVerticalListAlertDialog(
    title: String,
    items: List<String>,
    onDisMiss: (Boolean) -> Unit,
    onClick: (String) -> Unit
) {
    var selectIdx by remember { mutableIntStateOf(0) }
    BasicAlertDialog(
        onDismissRequest = { onDisMiss(false) }
    ) {
        ItemTitleCard(title = title) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(items.size) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                selectIdx = it
                                onClick(items[it])
                            },
                        text = items[it],
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}