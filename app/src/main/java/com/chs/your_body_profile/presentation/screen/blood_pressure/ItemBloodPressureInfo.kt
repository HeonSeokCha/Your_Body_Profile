package com.chs.your_body_profile.presentation.screen.blood_pressure

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.presentation.common.ItemDetailInfo
import com.chs.your_body_profile.presentation.common.ItemSimpleInfo
import com.chs.your_body_profile.presentation.ui.theme.DiastolicFont
import com.chs.your_body_profile.presentation.ui.theme.SystolicFont

@Composable
fun ItemSimpleBloodPressureInfo(
    bloodPressureInfo: BloodPressureInfo,
    onClick: (BloodPressureInfo) -> Unit,
    onLongClick: (BloodPressureInfo) -> Unit
) {
    ItemSimpleInfo(
        title = "${bloodPressureInfo.systolic}/${bloodPressureInfo.diastolic}",
        measureUnit = stringResource(id = R.string.text_blood_sugar_unit),
        subTitle = bloodPressureInfo.measureDateTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = { onClick(bloodPressureInfo) },
        onLongClick = { onLongClick(bloodPressureInfo) },
        subComposable = {}
    )
}

@Composable
fun ItemDetailBloodPressureInfo(
    bloodPressureInfo: BloodPressureInfo,
    onDismiss: () -> Unit,
) {
    ItemDetailInfo(
        dateTime = bloodPressureInfo.measureDateTime,
        subComposable = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(0.3f),
                        text = stringResource(R.string.text_systolic),
                        color = SystolicFont,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End
                    )

                    Text(
                        modifier = Modifier.weight(0.4f),
                        text = bloodPressureInfo.systolic.toString(),
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        modifier = Modifier.weight(0.3f),
                        text = stringResource(R.string.text_blood_pressure_unit),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(0.3f),
                        text = stringResource(R.string.text_diastolic),
                        color = DiastolicFont,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End
                    )

                    Text(
                        modifier = Modifier.weight(0.4f),
                        text = bloodPressureInfo.diastolic.toString(),
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        modifier = Modifier.weight(0.3f),
                        text = stringResource(R.string.text_blood_pressure_unit),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        onDismiss = onDismiss
    )
}