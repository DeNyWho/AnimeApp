package com.example.anibox.presentation.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.anibox.R
import com.example.anibox.navigation.Screen
import com.example.anibox.ui.theme.Purple500
import com.example.anibox.ui.theme.Purple700

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel,
) {

    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val onLoginCompleted by splashViewModel.onLogin.collectAsState()


    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 2000,
                delayMillis = 400
            )
        )
        navController.popBackStack()
        if(onLoginCompleted){
            navController.navigate(Screen.Home.route)
        } else {

            if (!onBoardingCompleted) {
                navController.navigate(Screen.Welcome.route)
            } else {
                navController.navigate(Screen.Login.route)
            }
        }
    }

    Splash(degrees = degrees.value)

}

@Composable
fun Splash(degrees: Float) {

    val modifier = if (isSystemInDarkTheme()){
        Modifier.background(Color.Black)
    } else {
        Modifier.background(
            Brush.verticalGradient(listOf(Purple700, Purple500))
        )
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.rotate(degrees = degrees),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "stringResource(R.string.logo)"
        )
        Spacer(modifier = Modifier.height(120.dp))
        Row(
            modifier = Modifier
                .offset(x = 0.dp, y = 90.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = "Anime",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(0.dp,0.dp,10.dp,0.dp )
            )
            Text(
                text = "Manga",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}


@Preview
@Composable
fun SplashScreenPreview() {
    Splash(degrees = 0f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash(degrees = 0f)
}
