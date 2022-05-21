package com.example.anibox.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.anibox.ui.theme.BlackBackground
import com.example.anibox.ui.theme.OnDarkSurface
import com.example.animeapp.presentation.composable.CenterCircularProgressIndicator

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    contentType: String?,
    malId: Int?,
    onBackPressed: () -> Boolean = { false }
) {
//    val toolbarScaffoldState = rememberCollapsingToolbarScaffoldState()
    val contentDetailsState = viewModel.contentDetailsState.value
    val genres = contentDetailsState.data?.genres ?: listOf()

    val largeImageCoil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = contentDetailsState.data?.images?.jpg?.largeImageUrl).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
            }).build()
    )

    val smallImageCoil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = contentDetailsState.data?.images?.jpg?.imageUrl).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
            }).build()
    )

    LaunchedEffect(
        key1 = contentType + malId,
        block = { viewModel.getContentDetails(contentType, malId) }
    )

    if (contentDetailsState.isLoading) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(BlackBackground)
        ) {
            CenterCircularProgressIndicator(
                size = 40.dp,
                color = Color.Red
            )
        }
    } else {
//        CollapsingToolbarScaffold(
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(BlackBackground),
//            state = toolbarScaffoldState,
//            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
//            toolbar = {
//                ContentDetailsScreenToolbar(
//                    largeCoil = largeImageCoil,
//                    smallCoil = smallImageCoil,
//                    contentDetailsState = contentDetailsState,
//                    toolbarScaffoldState = toolbarScaffoldState,
//                    onArrowClick = onBackPressed
//                )
//            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {

                items(7) {
                    Text(
                        text = "Anime Detail's",
                        style = TextStyle(
                            color = OnDarkSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.height(220.dp)
                    )
                }
            }
        }
    }
}
