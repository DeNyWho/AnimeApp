package com.example.anibox.presenter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.anibox.composable.CustomTextField
import com.example.anibox.presenter.home.composable.SearchEditText
import com.example.anibox.presenter.home.composable.SearchLeadingIcon
import com.example.anibox.ui.theme.BlackBackground
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


@OptIn(ExperimentalCoilApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
    lazyColumnState: LazyListState = rememberLazyListState(),
    onSearchFieldClick: () -> Unit = { },
    onContentClick: (String, Int) -> Unit = { type, id -> },
    modifier: Modifier = Modifier,
) {

//    LoadAnime()
    val now = Clock.System.now()
    val year = now.toLocalDateTime(TimeZone.currentSystemDefault()).year
    LaunchedEffect(viewModel) {
        withContext(Dispatchers.IO) {
//            viewModel.getAnimeAiringPopular()
            delay(1500)
            viewModel.getAnimeWinter(year = year, season = "winter")
            delay(1500)
            viewModel.getAnimeSummer(year = year, season = "summer")
            delay(1500)
            viewModel.getAnimeSpring(year = year, season = "spring")
            delay(1500)
            viewModel.getAnimeAutumn(year = year, season = "fall")
        }
    }

    val animeAiringPopularState = viewModel.animeAiringPopular.value
    val animeAutumn = viewModel.animeSeasonAutumn.value
    val animeSpring = viewModel.animeSeasonSpring.value
    val animeWinter = viewModel.animeSeasonWinter.value
    val animeSummer = viewModel.animeSeasonSummer.value

    val snackbarHostState = remember { SnackbarHostState() }

    val snackbarChannel = remember { Channel<String>(Channel.CONFLATED) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBackground),
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            onSearchFieldClick()
                        },
                    padding = PaddingValues(12.dp),
                    content = {
                        SearchEditText(
                            fieldPlaceholder = "Try 'One Piece'",
                        )
                    },
                    leadingIcon = {
                        SearchLeadingIcon(
                            size = 16.dp,
                            padding = PaddingValues(end = 8.dp)
                        )
                    }
                )
            }

            HomeContentList(
                animeSeasonAutumn = animeAutumn,
                animeSeasonWinter = animeWinter,
                animeSeasonSpring = animeSpring,
                animeSeasonSummer = animeSummer,
//                animeAiringPopularState = animeAiringPopularState,
                onTopAnimeClick = onContentClick,
                lazyColumnState = lazyColumnState
            )
        }
//    if (isSystemInDarkTheme()) {
//        Column(modifier = Modifier
//            .background(Color.Black)
//            .fillMaxSize()) {
//            Scaffold(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(BlackBackground),
//                scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)
//            ) {
//                Column(modifier = Modifier.fillMaxWidth()) {
//                    Box(modifier = Modifier.fillMaxWidth()) {
//                        CustomTextField(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(24.dp, 12.dp)
//                                .clip(RoundedCornerShape(8.dp))
//                                .clickable {
//                                    onSearchFieldClick()
//                                },
//                            padding = PaddingValues(12.dp),
//                            content = {
//                                SearchEditText(
//                                    fieldPlaceholder = "Try something new",
//                                )
//                            },
//                            leadingIcon = {
//                                SearchLeadingIcon(
//                                    size = 16.dp,
//                                    padding = PaddingValues(end = 8.dp)
//                                )
//                            }
//                        )
//                    }
//                }
//                Divider(
//                    color = BlackLighterBackground,
//                    thickness = 1.dp,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                HomeContentList(
//                    animeAiringPopularState = animeAiringPopularState,
////                    animeScheduleState = animeScheduleState,
////                    animeTopState = animeTopState,
//                    onTopAnimeClick = onContentClick,
//                    lazyColumnState = lazyColumnState
//                )
//            }
//        }
//    } else {
//        Column(modifier = Modifier
//            .background(Color.White)
//            .fillMaxSize()) {
//            Scaffold(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.White),
//                scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)
//            ) {
//                Column(modifier = Modifier.fillMaxWidth()) {
//                    Box(modifier = Modifier.fillMaxWidth()) {
//                        CustomTextField(
//                                Modifier
//                                    .fillMaxWidth()
//                                    .padding(24.dp, 12.dp)
//                                    .clip(RoundedCornerShape(8.dp))
//                                    .clickable {
//                                        onSearchFieldClick()
//                                    },
//                            padding = PaddingValues(12.dp),
//                            content = {
//                                SearchEditText(
//                                    fieldPlaceholder = "Try something new",
//                                )
//                            },
//                            leadingIcon = {
//                                SearchLeadingIcon(
//                                    size = 16.dp,
//                                    padding = PaddingValues(end = 8.dp)
//                                )
//                            }
//                        )
//                    }
//                }
//                Divider(
//                    color = BlackLighterBackground,
//                    thickness = 1.dp,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//            }
//        }
//    }
    }
}
