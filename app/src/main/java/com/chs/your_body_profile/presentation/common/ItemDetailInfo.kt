package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toCommaFormat
import com.chs.your_body_profile.presentation.ui.theme.YourBodyProfileTheme
import java.time.LocalDateTime

@Composable
fun ItemSimpleInfo(
    title: String,
    measureUnit: String,
    subTitle: String,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    subComposable: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 8.dp
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
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


@Composable
fun ItemDetailInfo(
    dateTime: LocalDateTime,
    subComposable: @Composable () -> Unit,
    onDismiss: () -> Unit
) {
    ItemDialog(
        onDismiss = onDismiss,
        subComposable = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 300.dp)
                        .padding(
                            top = 24.dp,
                            bottom = 36.dp
                        )
                ) {
                    subComposable()
                }

                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(
                            shape = CircleShape,
                            color = CardDefaults.cardColors().containerColor
                        )
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = CircleShape
                        ),
                    onClick = { onDismiss() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                    )
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(text = dateTime.format(Constants.YEAR_DATE_FORMATTER_DETAIL))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewItemDetailInfo() {
    YourBodyProfileTheme {
        ItemDetailInfo(
            dateTime = LocalDateTime.now(),
            subComposable = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "결제내역")

                    Text(text = "방배 우리 내과")

                    Text(text = "₩ ${(30000L).toCommaFormat()}")

                    Text(text = "술 처먹고 병신짓 완료.")
                }
            },
            onDismiss = {}
        )
    }
}