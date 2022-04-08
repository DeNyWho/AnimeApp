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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.animeapp.domain.model.OnBoardingPage
import com.example.animeapp.navigation.Screen
import com.example.animeapp.ui.theme.activeIndicatorColor
import com.example.animeapp.ui.theme.borderLogin
import com.example.animeapp.ui.theme.buttonSignUp
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
        Color.Black
    } else Color.White

    val textColor = if(isSystemInDarkTheme()){
        Color.White
    } else Color.Black

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val scope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
        if(!isSystemInDarkTheme()) {

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = onBoardingPage.title,
                    fontSize = 25.sp,
                    color = textColor
                )
                Text(
                    text = onBoardingPage.description,
                    fontSize = 25.sp,
                    color = textColor
                )
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
                    activeColor = MaterialTheme.colors.activeIndicatorColor,
                    modifier = Modifier
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight(0.22f)
                    .padding(50.dp, 0.dp, 50.dp, 20.dp)
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate(Screen.SignUp.route) },
                    Modifier
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
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.22f)
                    .padding(50.dp, 0.dp, 50.dp, 20.dp)
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = { navController.navigate(Screen.SignUp.route) },
                    Modifier
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

//        TextButton(
//            onClick = { navController.navigate(Screen.Home.route) },
//            modifier = Modifier
//                .padding(10.dp)
//                .align(Alignment.TopEnd)
//        ) {
//            Text(
//                text = "Skip",
//                fontSize = 20.sp,
//                color = Color.White,
//            )
//        }
//        Row(modifier = Modifier
//            .fillMaxWidth()
//            .padding(4.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Card(
//                shape = RoundedCornerShape(20.dp),
//                modifier = Modifier
//                    .padding(12.dp)
//                    .fillMaxSize(0.4f)
//            ) {
//                Image(
//                    painter = painterResource(id = onBoardingPage.image),
//                    contentScale = ContentScale.Crop,
//                    contentDescription = null
//                )
//            }
//            Card(
//                modifier = Modifier
//                    .fillMaxSize(0.4f)
//                    .padding(12.dp),
//                shape = RoundedCornerShape(20.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = onBoardingPage.image),
//                    contentScale = ContentScale.Crop,
//                    contentDescription = null
//                )
//            }
//            Card(
//                modifier = Modifier
//                    .fillMaxSize(0.4f)
//                    .padding(12.dp),
//                shape = RoundedCornerShape(20.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = onBoardingPage.image),
//                    contentScale = ContentScale.Crop,
//                    contentDescription = null
//                )
//            }
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
////                  shape = RoundedCornerShape(20.dp)
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
////        Text(
////            text = "Skip",
////            fontSize = 20.sp,
////            color = Color.Black,
////            modifier = Modifier
////                .align(Alignment.TopEnd)
////                .padding(15.dp)
////        )

}





