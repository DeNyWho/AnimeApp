package com.example.animeapp.presentation.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.animeapp.domain.model.OnBoardingPage
import com.example.animeapp.ui.theme.activeIndicatorColor
import com.example.animeapp.ui.theme.inactiveIndicatorColor
import com.example.animeapp.util.Constants.ON_BOARDING_PAGE_COUNT
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeScreenViewModel: WelcomeScreenViewModel = hiltViewModel()
) {

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            count = ON_BOARDING_PAGE_COUNT,
            verticalAlignment = Alignment.Top
        ) {
            BodySection(
                navController = navController,
                welcomeScreenViewModel = welcomeScreenViewModel,
                pagerState = pagerState,
                onBoardingPage = pages[it]
            )
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BodySection(
    navController: NavHostController,
    welcomeScreenViewModel: WelcomeScreenViewModel,
    pagerState: PagerState,
    onBoardingPage: OnBoardingPage
) {

    val color = if (isSystemInDarkTheme()) {
        Color.White
    } else Color.White

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.weight(0.5f)
            ) {
                Image(
                    painter = painterResource(id = onBoardingPage.image),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            Card(
                modifier = Modifier.weight(0.5f),
                shape = RoundedCornerShape(20.dp)
            ) {
                Image(
                    painter = painterResource(id = onBoardingPage.image),

                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            Card(
                modifier = Modifier.weight(0.5f),
                shape = RoundedCornerShape(20.dp)
            ) {
                Image(
                    painter = painterResource(id = onBoardingPage.image),

                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
//        Row(
//            modifier = Modifier
//                .fillMaxSize(0.3f)
////                .padding(15.dp, 10.dp, 0.dp, 0.dp)
//        ) {
////            Card(
////                modifier = Modifier
////                    .height(200.dp)
////                    .width(150.dp),
////                shape = RoundedCornerShape(20.dp)
////            ) {
//                Image(
//                    painter = painterResource(id = onBoardingPage.image),
//
//                    contentScale = ContentScale.Crop,
//                    contentDescription = null
//                )
////            }
//
////            Card(
////                modifier = Modifier
////                    .height(200.dp)
////                    .width(150.dp),
////                shape = RoundedCornerShape(20.dp)
////            ) {
//                Image(
//                    painter = painterResource(id = onBoardingPage.image),
//
//                    contentScale = ContentScale.Crop,
//                    contentDescription = null
//                )
////            }
//
////            Card(
////                modifier = Modifier
////                    .height(200.dp)
////                    .width(150.dp),
////                shape = RoundedCornerShape(20.dp)
////            ) {
//                Image(
//                    painter = painterResource(id = onBoardingPage.image),
//
//                    contentScale = ContentScale.Crop,
//                    contentDescription = null
//                )
////            }
//
//        }
////        Card(
////            modifier = Modifier
////                .fillMaxWidth()
////                .fillMaxHeight(0.25f)
////                .align(Alignment.BottomCenter),
////            shape = RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)
////        ) {
////            Text(
////                text = onBoardingPage.title,
////                fontSize = 20.sp,
////                color = Color.Black,
////                modifier = Modifier
////                    .align(Alignment.TopStart)
////                    .padding(15.dp)
////            )
////            Text(
////                text = onBoardingPage.description,
////                fontSize = 20.sp,
////                color = Color.Black,
////                modifier = Modifier
////                    .align(Alignment.TopStart)
////                    .padding(15.dp, 50.dp, 0.dp, 0.dp)
////            )
////        }
////        HorizontalPagerIndicator(
////            pagerState = pagerState,
////            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
////            activeColor = MaterialTheme.colors.activeIndicatorColor,
////            modifier = Modifier
////                .padding(20.dp)
////                .align(Alignment.BottomStart)
////        )
////        Text(
////            text = "Skip",
////            fontSize = 20.sp,
////            color = Color.Black,
////            modifier = Modifier
////                .align(Alignment.TopEnd)
////                .padding(15.dp)
////        )

    }


}

