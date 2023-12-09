package com.imagefinder.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryButtonText(
    stringResource: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(stringResource).uppercase(),
        color = Color.White,
        fontSize = 16.sp
    )
}
