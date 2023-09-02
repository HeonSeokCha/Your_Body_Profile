package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemDetailInfo(
    title: String,
    measureUnit: String,
    subTitle: String,
    subComposable: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row {
                Text(
                    text = title,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = measureUnit,
                    fontSize = 24.sp
                )
            }
            Text(
                text = subTitle,
                color = Color.Gray,
                fontSize = 20.sp
            )
        }

        subComposable()
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showSystemUi = false, showBackground = true)
@Composable
fun PreviewItemDetailInfo() {
    ItemDetailInfo(title = "88", measureUnit = "mg/dL", subTitle = "23:33") {
        Icon(imageVector = Icons.Filled.WbSunny, contentDescription = null)
    }
}