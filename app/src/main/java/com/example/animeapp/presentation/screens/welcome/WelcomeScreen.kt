package com.example.animeapp.presentation.screens.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.animeapp.R
import com.example.animeapp.domain.model.OnBoardingPage
import com.example.animeapp.navigation.Screen
import com.example.animeapp.ui.theme.*
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

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        HorizontalPager(
            state = pagerState,
            count = ON_BOARDING_PAGE_COUNT,
            verticalAlignment = Alignment.Top
        ) {
            BodySection(
                pagerState = pagerState,
                onBoardingPage = pages[it]
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.25f)
                .padding(50.dp, 0.dp, 50.dp, 20.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isSystemInDarkTheme()) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
                    activeColor = MaterialTheme.colors.activeIndicatorColor,
                    modifier = Modifier.padding(20.dp),
                    spacing = 20.dp
                )
                Button(
                    onClick = {
                        welcomeScreenViewModel.saveOnBoardingState(true)
                        navController.navigate(Screen.SignUp.route)
                    },
                    Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = buttonSignUp,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(40.dp),
                ) {
                    Text(
                        text = "Sign up",
                        fontSize = 17.sp
                    )
                }
                Button(
                    onClick = {
                        welcomeScreenViewModel.saveOnBoardingState(true)
                        navController.navigate(Screen.SignUp.route)
                    },
                    Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .fillMaxWidth(),
                    border = BorderStroke(1.dp, borderLogin),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(40.dp),
                ) {
                    Text(
                        text = "Login",
                        fontSize = 17.sp
                    )
                }
            } else {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
                    activeColor = MaterialTheme.colors.activeIndicatorColor,
                    modifier = Modifier.padding(20.dp),
                    spacing = 20.dp
                )
                Button(
                    onClick = {
                        welcomeScreenViewModel.saveOnBoardingState(true)
                        navController.navigate(Screen.SignUp.route)
                    },
                    Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = buttonSignUp,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(40.dp),
                ) {
                    Text(
                        text = "Sign up",
                        fontSize = 17.sp
                    )
                }
                Button(
                    onClick = {
                        welcomeScreenViewModel.saveOnBoardingState(true)
                        navController.navigate(Screen.SignUp.route)
                    },
                    Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .fillMaxWidth(),
                    border = BorderStroke(1.dp, Color.White),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(40.dp),
                ) {
                    Text(
                        text = "Login",
                        fontSize = 17.sp
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BodySection(
    pagerState: PagerState,
    onBoardingPage: OnBoardingPage
) {

    val color = if (isSystemInDarkTheme()) {
        Color.Black
    } else Color.White

    val textColor = if (isSystemInDarkTheme()) {
        Color.White
    } else Color.Black

    val deskColor = if (isSystemInDarkTheme()) {
        backText
    } else Color.Black


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
//        if(pagerState.currentPage == 0) {
        when(pagerState.currentPage) {
            0 -> {
                if (!isSystemInDarkTheme()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(-7f)
                    ) {
                        Row(modifier = Modifier.padding(start = 20.dp)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.16f)
                                    .graphicsLayer(
                                        translationY = -55F
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.firstImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -95F,
                                        translationX = 45f
                                    )
                                    .fillMaxSize(0.25f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.secondImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            // reworked
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 95f
                                    )
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.16f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.thirdImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxSize(0.25f)
                                    .padding(start = 10.dp)
                                    .graphicsLayer(
                                        translationY = -95F,
                                        translationX = 115f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.fourthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                        Row(modifier = Modifier.padding(start = 20.dp)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.35f)
                                    .graphicsLayer(
                                        translationY = -215F,
                                        translationX = 5f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.fifthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 45f
                                    )
                                    .fillMaxHeight(0.25f)
                                    .fillMaxWidth(0.35f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id =onBoardingPage.sixthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -225F,
                                        translationX = 95f
                                    )
                                    .fillMaxWidth(0.40f)
                                    .fillMaxHeight(0.35f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.seventhImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            // reworked
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxSize(0.25f)
                                    .padding(start = 10.dp)
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 115f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.eighthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.onboarding1v1a),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(0.2f),
                            alignment = Alignment.BottomCenter
                        )
                        Text(
                            text = onBoardingPage.title,
                            fontSize = 24.sp,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = onBoardingPage.description,
                            fontSize = 20.sp,
                            color = deskColor,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(-7f)
                    ) {
                        Row(modifier = Modifier.padding(start = 20.dp)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.16f)
                                    .graphicsLayer(
                                        translationY = -55F
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.firstImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -95F,
                                        translationX = 45f
                                    )
                                    .fillMaxSize(0.25f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.secondImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            // reworked
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 95f
                                    )
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.16f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.thirdImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxSize(0.25f)
                                    .padding(start = 10.dp)
                                    .graphicsLayer(
                                        translationY = -95F,
                                        translationX = 115f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.fourthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                        Row(modifier = Modifier.padding(start = 20.dp)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.35f)
                                    .graphicsLayer(
                                        translationY = -215F,
                                        translationX = 5f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.fifthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 45f
                                    )
                                    .fillMaxHeight(0.25f)
                                    .fillMaxWidth(0.35f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.sixthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -225F,
                                        translationX = 95f
                                    )
                                    .fillMaxWidth(0.40f)
                                    .fillMaxHeight(0.35f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.seventhImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            // reworked
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxSize(0.25f)
                                    .padding(start = 10.dp)
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 115f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.eighthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.onboarding1v1b),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(0.2f),
                            alignment = Alignment.BottomCenter
                        )
                        Text(
                            text = onBoardingPage.title,
                            fontSize = 24.sp,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = onBoardingPage.description,
                            fontSize = 20.sp,
                            color = deskColor,
                            textAlign = TextAlign.Center
                        )
                    }
                }
        }
            1 -> {
                if (!isSystemInDarkTheme()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(-7f)
                    ) {
                        Row(modifier = Modifier.padding(start = 20.dp)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.16f)
                                    .graphicsLayer(
                                        translationY = -55F
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.firstImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -95F,
                                        translationX = 45f
                                    )
                                    .fillMaxSize(0.25f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.secondImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            // reworked
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 95f
                                    )
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.16f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.thirdImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxSize(0.25f)
                                    .padding(start = 10.dp)
                                    .graphicsLayer(
                                        translationY = -95F,
                                        translationX = 115f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.fourthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                        Row(modifier = Modifier.padding(start = 20.dp)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.35f)
                                    .graphicsLayer(
                                        translationY = -215F,
                                        translationX = 5f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.fifthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 45f
                                    )
                                    .fillMaxHeight(0.25f)
                                    .fillMaxWidth(0.35f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.sixthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -225F,
                                        translationX = 95f
                                    )
                                    .fillMaxWidth(0.40f)
                                    .fillMaxHeight(0.35f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.seventhImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            // reworked
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxSize(0.25f)
                                    .padding(start = 10.dp)
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 115f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = onBoardingPage.eighthImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.onboarding1v1a),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(0.2f),
                            alignment = Alignment.BottomCenter
                        )
                        Text(
                            text = onBoardingPage.title,
                            fontSize = 24.sp,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = onBoardingPage.description,
                            fontSize = 20.sp,
                            color = deskColor,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(-7f)
                    ) {
                        Row(modifier = Modifier.padding(start = 20.dp)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.16f)
                                    .graphicsLayer(
                                        translationY = -55F
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mangas_min),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -95F,
                                        translationX = 45f
                                    )
                                    .fillMaxSize(0.25f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.yournamemanga_min),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            // reworked
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 95f
                                    )
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.16f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.voleyballmanga_min),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxSize(0.25f)
                                    .padding(start = 10.dp)
                                    .graphicsLayer(
                                        translationY = -95F,
                                        translationX = 115f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.toradoramanga_min),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                        Row(modifier = Modifier.padding(start = 20.dp)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth(0.25f)
                                    .fillMaxHeight(0.35f)
                                    .graphicsLayer(
                                        translationY = -215F,
                                        translationX = 5f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.dragonmanga_min),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 45f
                                    )
                                    .fillMaxHeight(0.25f)
                                    .fillMaxWidth(0.35f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.moonmanga_min),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .graphicsLayer(
                                        translationY = -225F,
                                        translationX = 95f
                                    )
                                    .fillMaxWidth(0.40f)
                                    .fillMaxHeight(0.35f)
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.catmanga_min),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            // reworked
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxSize(0.25f)
                                    .padding(start = 10.dp)
                                    .graphicsLayer(
                                        translationY = -55F,
                                        translationX = 115f
                                    )
                                    .weight(1f),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.tokyoghoulmanga_min),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.onboarding1v1b),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(0.2f),
                            alignment = Alignment.BottomCenter
                        )
                        Text(
                            text = onBoardingPage.title,
                            fontSize = 24.sp,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = onBoardingPage.description,
                            fontSize = 20.sp,
                            color = deskColor,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}





