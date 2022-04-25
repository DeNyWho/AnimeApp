package com.example.animeapp.presentation.screens.account.signUp

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.navigation.Screen
import com.example.animeapp.presentation.screens.account.UserViewModel
import com.example.animeapp.ui.theme.Gray
import com.example.animeapp.ui.theme.bluer
import com.example.animeapp.ui.theme.lighterGray
import com.example.animeapp.ui.theme.orange
import kotlinx.coroutines.channels.Channel

@Composable
fun SignUp(
    navController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val signState = userViewModel.signState.value
    val snackbarChannel = remember { Channel<String?>(Channel.CONFLATED) }



    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp))
                .background(Color.White)
                .align(Alignment.BottomEnd)
                .fillMaxSize(),
        ) {
            Text(
                text = "Create an Account",
                color = Color.Black,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Create your free AniBox account",
                color = Color.LightGray,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(Alignment.CenterHorizontally)
            )

            var name by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                shape = RoundedCornerShape(10.dp),
                value = name,
                onValueChange = { name = it },
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
                label = { Text("Type your name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        tint = orange,
                        contentDescription = "emailIcon"
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 40.dp)
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
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 15.dp)
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
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 15.dp)
            )

            var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
            var confirmPasswordVisible by remember { mutableStateOf(false) }
            TextField(
                shape = RoundedCornerShape(10.dp),
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
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
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                label = { Text("Confirm password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        tint = orange,
                        contentDescription = "passwordIcon"
                    )
                },
                trailingIcon = {
                    val image = if (confirmPasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description =
                        if (confirmPasswordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 15.dp)
            )
            val context = LocalContext.current
            Button(
                onClick = {
                    val user = UserDto(
                        email = email.text,
                        password = password.text,
                        name = name.text
                    )
                    userViewModel.getUserSignUp(user)

                    with(signState.error.getContentIfNotHandled()){
                        snackbarChannel.trySend(this)
                    }
                    if(signState.result) {
                        navController.navigate(Screen.Home.route)
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = bluer,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 25.dp)
                    .height(50.dp),
            ) {
                Text(text = "Join AniBox", fontSize = 20.sp)
            }
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
                .padding(start = 25.dp, end = 25.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "I already have an account",
                modifier = Modifier.padding(bottom = 13.dp)
            )
            TextButton(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .align(Alignment.Bottom),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                onClick = {
                    navController.navigate(Screen.Login.route)
                }
            ) {
                Text(text = "Sign In", color = orange)
            }
        }
    }
}

