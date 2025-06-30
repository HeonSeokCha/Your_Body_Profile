package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo
import com.chs.your_body_profile.presentation.common.ItemSimpleInfo

@Composable
fun ItemSimpleBloodSugarInfo(
    bloodSugarInfo: BloodSugarInfo,
    onClick: (BloodSugarInfo) -> Unit,
    onLongClick: (BloodSugarInfo) -> Unit
) {
    ItemSimpleInfo(
        title = bloodSugarInfo.number.toString(),
        measureUnit = stringResource(id = R.string.text_blood_sugar_unit),
        subTitle = bloodSugarInfo.measureDateTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = { onClick(bloodSugarInfo) },
        onLongClick = { onLongClick(bloodSugarInfo) }
    ) {
        Row {
            Text(text = bloodSugarInfo.measureTypeIdx.mean.second)
//            Icon(imageVector = measureInfo.second, contentDescription = null)
        }
    }
}

@Composable
fun ItemDetailBloodSugarInfo(
    bloodSugarInfo: BloodSugarInfo,
    onDismiss: () -> Unit
) {

    ItemDetailInfo(
        dateTime = bloodSugarInfo.measureDateTime,
        subComposable = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = bloodSugarInfo.number.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = stringResource(R.string.text_blood_sugar_unit),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

//                Image(
//                    modifier = Modifier.size(32.dp),
//                    imageVector = Constants.bloodSugarMeasureList[bloodSugarInfo.measureTypeIdx].second,
//                    colorFilter = ColorFilter.tint(Color.Gray),
//                    contentDescription = null
//                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = bloodSugarInfo.measureTypeIdx.mean.second,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Color.Gray
                )
            }
        },
        onDismiss = onDismiss
    )
}