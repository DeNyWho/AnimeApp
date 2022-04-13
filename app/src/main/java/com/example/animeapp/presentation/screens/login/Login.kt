package com.example.animeapp.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.animeapp.R
import com.example.animeapp.navigation.Screen
import com.example.animeapp.ui.theme.Gray
import com.example.animeapp.ui.theme.bluer
import com.example.animeapp.ui.theme.lighterGray
import com.example.animeapp.ui.theme.orange

//navController: NavHostController
@Composable
fun Login(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if(!isSystemInDarkTheme()) {
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
                    .fillMaxHeight(0.7f),
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

                var email by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    shape = RoundedCornerShape(10.dp),
                    value = email,
                    onValueChange = { email = it },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        backgroundColor = lighterGray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedLabelColor = Gray
                    ),
                    singleLine = true,
                    label = { Text("Type your email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            tint = orange,
                            contentDescription = "emailIcon"
                        )
                    },
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 40.dp)
                )
                var password by remember { mutableStateOf(TextFieldValue("")) }
                var passwordVisible by remember { mutableStateOf(false) }
                TextField(
                    shape = RoundedCornerShape(10.dp),
                    value = password,
                    onValueChange = { password = it },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        backgroundColor = lighterGray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedLabelColor = Gray
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    label = { Text("Type your password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            tint = orange,
                            contentDescription = "passwordIcon"
                        )
                    },
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, contentDescription = description)
                        }
                    },
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 15.dp)
                )

                Button(
                    onClick = {
/*TODO{ Here need action on login button }*/
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = bluer,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.25f)
                        .padding(start = 25.dp, end = 25.dp, top = 25.dp),
                ) {
                    Text(text = "Login", fontSize = 20.sp)
                }
                /*TODO{ uncomment after adding google auth}*/
//            Row(modifier = Modifier
//                .fillMaxHeight(0.2f)
//                .padding(top = 20.dp, start = 25.dp, end = 25.dp)) {
//
//                Divider(
//                    color = lighterGray,
//                    thickness = 2.dp,
//                    modifier = Modifier
//                        .fillMaxWidth(0.3f)
//                        .weight(1f)
//                        .align(CenterVertically)
//                )
//
//                Text(
//                    text = "or login with",
//                    fontSize = 16.sp,
//                    color = Gray,
//                    modifier = Modifier
//                        .weight(1f)
//                        .align(Top)
//                        .padding(start = 15.dp)
//                )
//
//                Divider(
//                    color = lighterGray,
//                    thickness = 2.dp,
//                    modifier = Modifier
//                        .fillMaxWidth(0.3f)
//                        .weight(1f)
//                        .align(CenterVertically)
//                )
//                Icon(painter = , contentDescription = )
//            }

                /*Todo{ need reworks }*/
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 25.dp, end = 25.dp, top = 25.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "I don't have an account",
                        modifier = Modifier.padding(bottom = 13.dp)
                    )
                    TextButton(
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Bottom),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Transparent,
                            backgroundColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        ),
                        onClick = {
                            navController.navigate(Screen.SignUp.route)
                        }
                    ) {
                        Text(text = "Register", color = orange)
                    }
                }
            }
        } else {

        }
    }
}
