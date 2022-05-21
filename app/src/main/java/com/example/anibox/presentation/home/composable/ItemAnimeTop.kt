package com.example.anibox.presentation.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.anibox.domain.model.anime.AnimeTop
import com.example.anibox.ui.theme.OnDarkSurface
import com.example.animeapp.presentation.composable.CenterCircularProgressIndicator
import timber.log.Timber

@ExperimentalCoilApi
@Composable
fun ItemAnimeTop(
    modifier: Modifier = Modifier,
    anime: AnimeTop,
    onItemClick: () -> Unit
) {
    val titleLines = remember { mutableStateOf(0) }

    Timber.d(anime.imageUrl)

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(anime.imageUrl)
            .crossfade(true)
            .allowHardware(false)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onItemClick()
                Timber.d("Клик клик")
            }
    ) {
        Box(
            modifier = Modifier
                .height(190.dp)
        ) {
            if (painter.state is AsyncImagePainter.State.Loading) {
                CenterCircularProgressIndicator(
                    strokeWidth = 2.dp,
                    size = 15.dp,
                    color = Color.Red
                )
            } else {
                Image(
                    painter = painter,
                    contentDescription = "Thumbnail",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp))
                        .align(Alignment.Center),
                )
            }
//            if (painter.state is ImagePainter.State.Loading) {
//                CenterCircularProgressIndicator(
//                    strokeWidth = 2.dp,
//                    size = 20.dp,
//                    color = Color.Yellow
//                )
//            }
//            Image(
//                painter = painter,
//                contentDescription = "Thumbnail",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(RoundedCornerShape(12.dp))
//            )
        }

        Text(
            text = anime.title,
            modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = OnDarkSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            ),
            onTextLayout = { res -> titleLines.value = res.lineCount }
        )

        for (index in titleLines.value..2) {
            Text(text = " ", style = TextStyle(fontSize = 14.sp))
        }
    }
}