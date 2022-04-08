package com.example.animeapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF1feeec)
val lightGray = Color(0xFF74777d)
val buttonSignUp = Color(0xFFf32a30)
val borderLogin = Color(0xFFe8e8e8)


val Colors.inactiveIndicatorColor
@Composable
get() = if(isLight) lightGray else Color.White

val Colors.activeIndicatorColor
@Composable
get() = if(isLight) Teal200 else Teal200