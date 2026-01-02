
package com.morarafrank.dailybrief.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.morarafrank.dailybrief.R
import com.morarafrank.dailybrief.ui.theme.Typography

@Composable
fun NoNewsUi(
    error: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.no_data_anim)
        )
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(200.dp),
            iterations = Int.MAX_VALUE,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = error,
            style = Typography.bodyMedium,
        )
    }
}