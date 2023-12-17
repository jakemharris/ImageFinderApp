package com.imagefinder.nontest.ui.searchScreen

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.imagefinder.R
import com.imagefinder.nontest.ui.Routes
import com.imagefinder.nontest.ui.common.PrimaryButtonText
import com.imagefinder.nontest.ui.common.ScreenTitle
import com.imagefinder.vm.SearchScreenViewState
import com.imagefinder.vm.SearchViewModel

@Composable
fun SearchScreen(
    navigation: NavController
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val state by viewModel.observableState.collectAsStateWithLifecycle()

    Column(
        Modifier
            .background(Color.White)
            .padding(5.dp)
            .fillMaxSize(),
    ) {

        val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
            StaggeredGridCells.Adaptive(minSize = 175.dp)
        } else {
            StaggeredGridCells.Fixed(2)
        }

        LazyVerticalStaggeredGrid(
            columns = cellConfiguration,
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                item(span = StaggeredGridItemSpan.FullLine, content = { HeaderContent(viewModel = viewModel, state = state) })
                items(state.listOfImages) { photo ->
                    AsyncImage(
                        model = photo.src?.medium,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onImageClick(photo.id)
                                navigation.navigate(Routes.DETAIL_SCREEN)
                            }
                            .wrapContentHeight()
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        AnimatedVisibility(visible = state.errorMessage?.isNotBlank() == true) {
            Text(text = state.errorMessage.toString(), color = Color.Black)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HeaderContent(viewModel: SearchViewModel, state: SearchScreenViewState) {
    val controller = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitle(resourceId = R.string.find_a_photo)
        Image(
            painter = painterResource(id = R.drawable.gallery),
            contentDescription = "",
            modifier = Modifier
                .padding(20.dp)
                .size(50.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(50.dp)
                .border(
                    BorderStroke(width = 1.dp, Color.Black.copy(alpha = .5f)),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier.padding(start = 10.dp)
            )
            BasicTextField(
                value = state.searchString,
                onValueChange = {
                    viewModel.onTextChange(it)
                },
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                textStyle = TextStyle(fontSize = 16.sp),
            )
            AnimatedVisibility(
                visible = state.searchString.isNotBlank(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(onClick = viewModel::clearSearchString) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Close"
                    )
                }
            }
        }

        AnimatedVisibility(visible = !state.isLoading) {
            TextButton(
                onClick = {
                    controller?.hide()
                    viewModel.onClickSearch() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(
                        color = Color.Blue.copy(alpha = .6f),
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                PrimaryButtonText(stringResource = R.string.search)
            }
        }

        AnimatedVisibility(
            visible = state.isLoading,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            CircularProgressIndicator()
        }
        Text(
            text = "${state.listOfImages.size} items found",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )
        Spacer(
            modifier = Modifier
                .background(Color.Gray)
                .height(1.dp)
        )
    }
}
