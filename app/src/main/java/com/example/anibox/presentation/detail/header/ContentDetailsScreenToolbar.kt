package com.example.anibox.presentation.detail.header

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.anibox.presentation.detail.state.ContentDetailsState
import com.example.anibox.ui.theme.BlackBackground
import com.example.anibox.ui.theme.OnDarkSurface
import com.example.anibox.ui.theme.OnDarkSurfaceLight
import com.example.animeapp.presentation.composable.CenterCircularProgressIndicator
import com.google.accompanist.insets.statusBarsPadding
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.CollapsingToolbarScope
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import kotlin.math.roundToInt

@Composable
fun CollapsingToolbarScope.ContentDetailsScreenToolbar(
    largeCoil: AsyncImagePainter = rememberAsyncImagePainter(model = null),
    smallCoil: AsyncImagePainter = rememberAsyncImagePainter(model = null),
    contentDetailsState: ContentDetailsState = ContentDetailsState(null),
    toolbarScaffoldState: CollapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState(),
    onArrowClick: () -> Boolean = { false }
) {
    val blockerColorGradients = listOf(
        BlackBackground.copy(alpha = 0.8F),
        BlackBackground.copy(alpha = 0.9F),
        BlackBackground
    )

    val isTitleVisible = toolbarScaffoldState.toolbarState.progress <= 0.25


    val headerCaptionIcon: ImageVector
    val headerCaptionDescription: String

//    if (contentDetailsState.data?.isAiring == true
//        || contentDetailsState.data?.isPublishing == true
//    ) {
//        headerCaptionIcon = ImageVector.vectorResource(id = MyIcons.Outlined.Clock4)
//        headerCaptionDescription = "Ongoing"
//    } else {
//        headerCaptionIcon = ImageVector.vectorResource(id = MyIcons.Outlined.DoubleCheck)
//        headerCaptionDescription = "Completed"
//    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .parallax(0.5f)
            .graphicsLayer {
                // change alpha of Image as the toolbar expands
                alpha = toolbarScaffoldState.toolbarState.progress
            },
    ) {

        // Parallax header background
        Box {
            Image(
                painter = largeCoil,
                contentDescription = "Heading Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(colors = blockerColorGradients)
                    )
            )

            // Header Content
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .then(Modifier.padding(top = 52.dp, start = 16.dp, end = 16.dp, bottom = 12.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Left cover image
                Box(
                    modifier = Modifier
                        .width(100.dp)
                ) {
                    if (smallCoil.state is AsyncImagePainter.State.Loading) {
                        CenterCircularProgressIndicator(
                            strokeWidth = 2.dp,
                            size = 20.dp,
                            color = Color.Red
                        )
                    }
                    Image(
                        painter = smallCoil,
                        contentDescription = "Thumbnail",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                // Header right content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = contentDetailsState.data?.title ?: "-",
                        style = TextStyle(
                            color = OnDarkSurfaceLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )

                    with(contentDetailsState.data) {
                        this?.authors?.firstOrNull()?.let { author ->
                            Text(
                                text = author.name,
                                style = TextStyle(
                                    color = OnDarkSurface,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            )
                        }

                        this?.studios?.firstOrNull()?.let { studio ->
                            Text(
                                text = studio.name,
                                style = TextStyle(
                                    color = OnDarkSurface,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }

//                    // Ongoing / Airing status
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//
//                        Icon(
//                            imageVector = headerCaptionIcon,
//                            contentDescription = headerCaptionDescription,
//                            tint = OnDarkSurface,
//                            modifier = Modifier
//                                .height(14.dp)
//                                .padding(end = 6.dp))
//
//                        Text(
//                            text = contentDetailsState.data?.status ?: "-",
//                            style = TextStyle(
//                                color = OnDarkSurface,
//                                fontWeight = FontWeight.Bold,
//                                fontSize = 13.sp
//                            )
//                        )
//                    }
                }
            }
        }
    }


    // Toolbar
    Row(
        modifier = Modifier.fillMaxWidth().statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onArrowClick() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back", tint = OnDarkSurfaceLight
            )
        }

        val density = LocalDensity.current
        val initialOffset = with(density) {
            40.dp.toPx().roundToInt()
        }
        val targetOffset = with(density) {
            -40.dp.toPx().roundToInt()
        }

        AnimatedVisibility(
            visible = isTitleVisible,
            enter = slideInVertically(
                initialOffsetY = { initialOffset },
                animationSpec = tween(
                    durationMillis = 800,
                    delayMillis = 50,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(initialAlpha = 0f),
            exit = slideOutVertically(
                targetOffsetY = { targetOffset },
                animationSpec = tween(
                    durationMillis = 800,
                    delayMillis = 50,
                    easing = LinearOutSlowInEasing
                )
            ) + fadeOut()
        ) {
            Text(
                text = contentDetailsState.data?.title ?: "-",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = TextStyle(
                    color = OnDarkSurfaceLight,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.weight(1f).padding(start = 8.dp, end = 12.dp)
            )
        }
    }
}

