package com.example.anibox.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.anibox.core.enum.ContentType
import com.example.anibox.presentation.home.anime_popular.AnimeAiringPopularHorizontalPager
import com.example.anibox.presentation.home.composable.ItemAnimeTop
import com.example.anibox.presentation.home.composable.ItemMangaTop
import com.example.anibox.presentation.home.data.AnimeTopState
import com.example.anibox.presentation.home.data.MangaTopState
import com.example.anibox.presentation.home.state.popular.AnimePopularState
import com.example.anibox.presentation.home.view_holder.ItemAnimeTopShimmer
import com.example.anibox.ui.theme.nunitoType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.unclippedBoundsInWindow

@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoilApi
@Composable
fun HomeContentList(
  animeAiringPopularState: AnimePopularState = AnimePopularState(),
  animeTopState: AnimeTopState = AnimeTopState(),
  mangaTopState: MangaTopState = MangaTopState(),
  lazyColumnState: LazyListState = rememberLazyListState(),
  navController: NavHostController,
  onContentClick: (String, Int) -> Unit

) {

  LazyColumn(
    state = lazyColumnState,
  ) {
    // Start of Currently popular anime
    // TODO: Rework horizontal pager to AnimeThisSeason pager
    item(key = "AnimeThisSeason") {
      val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Custom)
      val pagerState = rememberPagerState()
      val itemCount = animeAiringPopularState.data.size.coerceAtMost(7)
      AnimeAiringPopularHorizontalPager(
        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
          val position = layoutCoordinates.unclippedBoundsInWindow()
          shimmerInstance.updateBounds(position)
        },
        pagerState = pagerState,
        data = animeAiringPopularState.data.slice(0 until itemCount),
        shimmerInstance = shimmerInstance,
        navController = navController
      )
    }


    item(key = "popularAnime") {
      Row(
        modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 4.dp, top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          modifier = Modifier.weight(1f),
          text = "Popular Anime",
          style = nunitoType.h1,
          fontWeight = FontWeight.Bold
        )

      }

      val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Custom)

      LazyRow(
        contentPadding = PaddingValues(12.dp, 0.dp, 12.dp, 0.dp),
        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
          val position = layoutCoordinates.unclippedBoundsInWindow()
          shimmerInstance.updateBounds(position)
        }
      ) {
        if (animeTopState.isLoading) {
          showShimmerPlaceholder(shimmerInstance)
        } else {
          items(animeTopState.data, key = { item -> item.malId }) { anime ->
            ItemAnimeTop(
              modifier = Modifier
                .width(160.dp)
                .padding(12.dp, 0.dp),
              anime = anime,
//            ) { onTopAnimeClick(ContentType.Anime.name, anime.malId) }
              onItemClick = { onContentClick(ContentType.Anime.name, anime.malId) }
            )
          }
        }
      }
    }

    item(key = "popularManga") {
      Row(
        modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          modifier = Modifier.weight(1f),
          text = "Popular Manga",
          style = nunitoType.h1,
          fontWeight = FontWeight.Bold
        )
      }

      val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Custom)

      LazyRow(
        contentPadding = PaddingValues(12.dp, 0.dp, 12.dp, 0.dp),
        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
          val position = layoutCoordinates.unclippedBoundsInWindow()
          shimmerInstance.updateBounds(position)
        }
      ) {
        if (mangaTopState.isLoading) {
          showShimmerPlaceholder(shimmerInstance)
        } else {
          items(mangaTopState.data, key = { item -> item.malId }) { manga ->
            ItemMangaTop(
              modifier = Modifier
                .width(160.dp)
                .padding(12.dp, 0.dp),
              manga = manga
            ) { onContentClick(ContentType.Manga.name, manga.malId) }
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(50.dp))
    }

  }
}

private fun LazyListScope.showShimmerPlaceholder(shimmerInstance: Shimmer, count: Int = 5) {
  items(count) {
    ItemAnimeTopShimmer(shimmerInstance)
  }
}