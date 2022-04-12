package com.example.animeapp.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.animeapp.R
//navController: NavHostController
@Composable
fun Login() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgroundlogin),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp))
                .background(Color.White)
                .align(Alignment.BottomEnd)
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
        ) {
            Text(
                text = "Login Account",
                color = Color.Black,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(CenterHorizontally)
            )
            Text(
                text = "Hello! Welcome back to the AniBox.",
                color = Color.LightGray,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(CenterHorizontally)
            )
        }
    }
}


@Preview
@Composable
fun LoginPrev() {
    Login()
}