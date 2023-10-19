package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chs.your_body_profile.R

@Composable
fun ItemInputBottomMenu(
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
            onClick = { onDismiss() }
        ) {
            Text(
                text = stringResource(id = R.string.text_cancel),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onClick = { onClick() }
        ) {
            Text(
                text = stringResource(id = R.string.text_confirm),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ItemInputButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(1f),
            onClick = { onClick() }
        ) {
            Text(
                text = stringResource(id = R.string.text_data_input),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}