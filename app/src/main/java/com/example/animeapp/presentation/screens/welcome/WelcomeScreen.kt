package com.example.animeapp.presentation.screens.welcome

import android.view.Gravity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
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
                .fillMaxHeight(0.2f)
                .padding(50.dp, 0.dp, 50.dp, 20.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isSystemInDarkTheme()) {
                Button(
                    onClick = { navController.navigate(Screen.SignUp.route) },
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
                    onClick = { navController.navigate(Screen.SignUp.route) },
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

                Button(
                    onClick = { navController.navigate(Screen.SignUp.route) },
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
                    onClick = { navController.navigate(Screen.SignUp.route) },
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

    val deskColor = if(isSystemInDarkTheme()){
        backText
    } else Color.Black


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
        if (!isSystemInDarkTheme()) {
            val shaping = RoundedCornerShape(10.dp)
            Column(modifier = Modifier.fillMaxSize()) {
                Row {
                    Card(
                        shape = shaping,
                        modifier = Modifier
                            .clip(shape = shaping)
                            .padding(5.dp)
                            .fillMaxSize(0.20f)
                            .weight(1f)
                            .rotate(-5f),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.petgirl),
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
                    modifier = Modifier.fillMaxSize(0.2f)
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
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
                    activeColor = MaterialTheme.colors.activeIndicatorColor,
                    modifier = Modifier.padding(40.dp),
                    spacing = 20.dp
                )
            }
        } else {
            Column {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.onboarding1v1b),
                        contentDescription = null
                    )
                    Text(
                        text = onBoardingPage.title,
                        fontSize = 30.sp,
                        color = textColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = onBoardingPage.description,
                        fontSize = 18.sp,
                        color = deskColor,
                        textAlign = TextAlign.Center
                    )
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
                        activeColor = MaterialTheme.colors.activeIndicatorColor,
                        modifier = Modifier.padding(40.dp),
                        spacing = 20.dp
                    )
                }
            }
        }
    }
}





