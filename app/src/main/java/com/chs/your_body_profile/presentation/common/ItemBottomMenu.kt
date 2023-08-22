package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ItemBottomMenu(
    modifier: Modifier,
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onClick = { onDismiss() }) {
            Text(
                text = "취소",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onClick = { onClick() }) {
            Text(
                text = "저장",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}