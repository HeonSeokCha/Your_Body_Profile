package com.chs.your_body_profile.presentation.screen.blood_pressure

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.presentation.ui.theme.DiastolicFont
import com.chs.your_body_profile.presentation.ui.theme.SystolicFont
import com.chs.your_body_profile.presentation.ui.theme.YourBodyProfileTheme
import java.time.LocalDateTime

@Composable
fun ItemBloodPressureSummaryInfo(
    list: List<BloodPressureInfo>
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
                    text = if (list.count() == 1) {
                        list.first().systolic.toString()
                    } else {
                        "${list.minBy { it.systolic }.systolic} - ${list.maxBy { it.systolic }.systolic}"
                    },
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
                    text = if (list.count() == 1) {
                        list.first().diastolic.toString()
                    } else {
                        "${list.minBy { it.diastolic }.diastolic} - ${list.maxBy { it.diastolic }.diastolic}"
                    },
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
    }
}


@Preview
@Composable
private fun PreviewSummary() {
    YourBodyProfileTheme {
        ItemBloodPressureSummaryInfo(
            listOf(
                BloodPressureInfo(
                    measureDateTime = LocalDateTime.now(),
                    systolic = 120,
                    diastolic = 80,
                    memo = null
                ),
                BloodPressureInfo(
                    measureDateTime = LocalDateTime.now(),
                    systolic = 110,
                    diastolic = 70,
                    memo = null
                )
            )
        )
    }
}