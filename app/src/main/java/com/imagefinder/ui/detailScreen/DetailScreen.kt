package com.imagefinder.ui.detailScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.imagefinder.R
import com.imagefinder.ui.common.PrimaryButtonText
import com.imagefinder.ui.common.ScreenTitle

@Composable
fun DetailScreen(
    navigation: NavController,
) {
    val viewModel: DetailViewModel = hiltViewModel()
    val state by viewModel.observableState.collectAsStateWithLifecycle()

    Column(
        Modifier
            .background(Color.White)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ScreenTitle(resourceId = R.string.details)

        MainContent(state = state, navigation = navigation)
    }
}

@Composable
private fun MainContent(
    state: DetailScreenViewState,
    navigation: NavController
) {
    val imageDetails = state.imageData

    AsyncImage(
        model = imageDetails?.src?.large,
        contentDescription = imageDetails?.alt,
        modifier = Modifier.fillMaxWidth(),
        placeholder = painterResource(R.drawable.gallery)
    )

    AnimatedVisibility(visible = state.isLoading) {
        CircularProgressIndicator()
    }

    AnimatedVisibility(visible = state.errorMessage.isNotBlank()) {
        ErrorContent(errorMessage = state.errorMessage)
    }

    Text(
        text = imageDetails?.alt ?: "No description",
        color = Color.Black,
        fontSize = 16.sp
    )
    Text(
        text = "Photographer: ${imageDetails?.photographer}",
        color = Color.Black,
        fontSize = 14.sp
    )

    TextButton(
        onClick = { navigation.popBackStack() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(
                color = Color.Blue.copy(alpha = .6f),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        PrimaryButtonText(stringResource = R.string.back)
    }
}

@Composable
private fun ErrorContent(errorMessage: String) {
    Text(text = errorMessage, color = Color.Black)
}

@Preview
@Composable
fun ErrorPreview() {
    ErrorContent(errorMessage = "Unable to show photo details at this time.")
}
