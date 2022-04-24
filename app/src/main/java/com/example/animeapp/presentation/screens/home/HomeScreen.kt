package com.example.animeapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import com.example.animeapp.presentation.composable.CustomTextField
import com.example.animeapp.presentation.screens.home.composable.SearchEditText
import com.example.animeapp.presentation.screens.home.composable.SearchLeadingIcon
import com.example.animeapp.ui.theme.BlackBackground
import com.example.animeapp.ui.theme.BlackLighterBackground
import kotlinx.coroutines.channels.Channel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
    lazyColumnState: LazyListState = rememberLazyListState(),
    onSearchFieldClick: (() -> Unit) = { },
    onContentClick: ((String, Int) -> Unit) = { type, id -> },
) {

    val animePopularState = viewModel.animeAiringPopular.value

    val snackbarHostState = remember{ SnackbarHostState()}

    // control error on snack ( one snack in one time )
    val snackbarChannel = remember { Channel<String>(Channel.CONFLATED)}
    
    LaunchedEffect(viewModel) {
        viewModel.getAnimePopular()
    }

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
                            fieldPlaceholder = "Try something new",
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
        }
        Divider(
            color = BlackLighterBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}