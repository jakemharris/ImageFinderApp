package com.imagefinder.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenTitle(
    modifier: Modifier = Modifier,
    resourceId: Int
) {
    Text(
        text = stringResource(resourceId),
        color = Color.Black,
        fontSize = 20.sp,
        modifier = modifier.padding(top = 20.dp)
    )
}
