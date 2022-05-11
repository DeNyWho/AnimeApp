package com.example.animeapp.presentation.screens.home.anime_popular

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.anibox.domain.model.anime.AnimeAiringPopular
import com.example.anibox.presentation.home.anime_popular.composable.PagerItemAnimeAiringPopular
import com.example.anibox.presentation.home.anime_popular.composable.ShimmerHorizontalChipIndicator
import com.example.anibox.presentation.home.anime_popular.composable.ShimmerPagerItemAnimeAiringPopular
import com.example.anibox.presentation.home.anime_popular.state.AnimeAiringPopularState
import com.example.anibox.ui.theme.Grey
import com.example.anibox.ui.theme.picker
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.valentinilk.shimmer.Shimmer
import timber.log.Timber
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun AnimeAiringPopularHorizontalPager(
    modifier: Modifier = Modifier,
    animeAiringPopularState: AnimeAiringPopularState = AnimeAiringPopularState(),
    pagerState: PagerState = rememberPagerState(),
    data: List<AnimeAiringPopular> = emptyList(),
    shimmerInstance: Shimmer,
//    onItemClick: (String, Int) -> Unit
) {
    val isLoading = animeAiringPopularState.isLoading
    val shownCount = if (isLoading) 3 else data.size

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp),
            state = pagerState,
            count = shownCount,
//            itemSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 64.dp),
            key = { page -> if (isLoading) page else data[page].malId }
        ) { page ->
            Timber.d("DATA = $data")
            Timber.d(data[page].imageUrl)
            if (isLoading) {
                ShimmerPagerItemAnimeAiringPopular(shimmerInstance = shimmerInstance, currentPage = page)
            } else {
                Timber.d("PAINTER")
                PagerItemAnimeAiringPopular(
                    modifier = Modifier,
                    data = data[page],
                    currentPage = page
                )
//                    onClick = { onItemClick(ContentType.Anime.name, data[page].malId) }
            }
        }

        /* horizontal chip pager indicator */
        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading) {
                ShimmerHorizontalChipIndicator(shimmerInstance)
            } else {
                repeat(data.size) {
                    val minWidth = 8.dp
                    val maxWidth = 24.dp
                    val rangeWidth = maxWidth - minWidth
                    val targetPage = pagerState.targetPage
                    val currentPage = pagerState.currentPage
                    val currentPageOffset = pagerState.currentPageOffset.absoluteValue
                    val targetPageOffset = ((currentPage + pagerState.currentPageOffset) - targetPage).absoluteValue

                    val animateWidth = when(it) {
                        currentPage -> minWidth + (rangeWidth * (1f - currentPageOffset.coerceIn(0f, 1f)))
                        targetPage -> minWidth + (rangeWidth * (1f - targetPageOffset.coerceIn(0f, 1f)))
                        else -> minWidth
                    }
                    val animateColor = animateColorAsState(
                        targetValue = if (it == pagerState.currentPage) {
                            picker
                        } else {
                            Grey
                        },
                        animationSpec = tween(
                            durationMillis = 400,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    Surface(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .width(animateWidth)
                            .height(8.dp),
                        color = animateColor.value,
                        shape = RoundedCornerShape(50f)
                    ) { }
                }
            }
        }
        /* End Of horizontal chip pager indicator */
    }
}