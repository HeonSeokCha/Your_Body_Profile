package com.chs.your_body_profile.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ItemConfirmDialog(
    title: String,
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = title) },
        confirmButton = {
            TextButton(onClick = { onClick() }) {
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("취소")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDialog(
    onDismiss: () -> Unit,
    subComposable: @Composable () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = { onDismiss() },
        content = subComposable
    )
}