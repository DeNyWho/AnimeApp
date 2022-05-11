package com.example.anibox.presentation.home.anime_popular.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.anibox.domain.model.anime.AnimeAiringPopular
import com.example.animeapp.presentation.composable.CenterCircularProgressIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import timber.log.Timber
import kotlin.math.absoluteValue

@OptIn(ExperimentalCoilApi::class, ExperimentalPagerApi::class)
@Composable
fun PagerScope.PagerItemAnimeAiringPopular(
  modifier: Modifier = Modifier,
  data: AnimeAiringPopular,
  currentPage: Int,
//  onClick: () -> Unit
) {

  Surface(
    color = Color.Transparent,
    modifier = Modifier
      .graphicsLayer {
        // Calculate the absolute offset for the current page from the
        // scroll position. We use the absolute value which allows us to mirror
        // any effects for both directions
        val pageOffset = calculateCurrentOffsetForPage(currentPage).absoluteValue
        // We animate the scaleX + scaleY, between 85% and 100%
        lerp(
          start = 0.95f,
          stop = 1f,
          fraction = 1f - pageOffset.coerceIn(0f, 1f)
        ).also { scale ->
          scaleX = scale
          scaleY = scale
        }

        // We animate the alpha, between 50% and 100%
        alpha = lerp(
          start = 0.1f,
          stop = 1f,
          fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
      }
      .fillMaxSize()
      .aspectRatio(
        ratio = 0.85f,
      )

  ) {
    Box(
      modifier = modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(12.dp))
//        .clickable { onClick() }
    ) {

      val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
          .data(data.imageUrl)
          .allowHardware(false)
          .size(Size.ORIGINAL) // Set the target size to load the image at.
          .build()
      )

      if (painter.state is AsyncImagePainter.State.Loading) {
        CenterCircularProgressIndicator(
          strokeWidth = 2.dp,
          size = 15.dp,
          color = Color.Red
        )
        Timber.d("STATE LOAD")
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
        Timber.d("ELSE BRANCH")
      }

      /* pager item title */
//      Box(
//        modifier = Modifier
//          .align(Alignment.BottomCenter)
//          .padding(horizontal = 12.dp, vertical = 6.dp)
//          .background(
//            color = BlackBlueBackground,
//            shape = RoundedCornerShape(8.dp),
//          )
//          .zIndex(2f)
//      ) {
//        Text(
//          text = data.title,
//          modifier = Modifier
//            .align(Alignment.BottomCenter)
//            .padding(horizontal = 12.dp, vertical = 6.dp),
//          maxLines = 1,
//          textAlign = TextAlign.Center,
//          overflow = TextOverflow.Ellipsis,
//          style = TextStyle(
//            color = OnDarkSurface,
//            fontSize = 14.sp,
//            fontWeight = FontWeight.Bold
//          ),
//        )
//      }
    }
  }
}