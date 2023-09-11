package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ItemSearchHistory(
    title: String,
    imageVector: ImageVector,
    onClick: (String) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 14.dp)
        .clickable {
            onClick(title)
        }
    ) {
        Icon(
            modifier = Modifier.padding(end = 10.dp),
            imageVector = imageVector,
            contentDescription = null
        )
        Text(text = title)
    }
}