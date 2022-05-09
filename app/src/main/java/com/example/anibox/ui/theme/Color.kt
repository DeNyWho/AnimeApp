package com.example.anibox.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF1feeec)
val lightGray = Color(0xFFdedede)
val active = Color(0xFF222222)
val indicator = Color(0xFF3d3d3d)
val Grey = Color(0xFF757575)
val blackestBack = Color(0xFF121212)
val BlackBackground = Color(0xFF20202A)
val BlackLighterBackground = Color(0xFF2B2B2B)
val BlackBlueBackground = Color(0xFF20202A)
val OnDarkSurfaceLight = Color(0xFFFFFFFF)
val OnDarkSurface = Color(0xFFBEBEBE)
val buttonSignUp = Color(0xFFf32a30)
val borderLogin = Color(0xFFe8e8e8)
val backText = Color(0xFFb0b0b0)
val orange = Color(0xFFf76743)
val lighterGray = Color(0xFFf5f5f5)
val Gray = Color(0xFFc4c4c4)
val bluer = Color(0xFF7579b5)
val smokyWhite = Color(0xFFf5f5f5)
val picker = Color(0xFF5468ff)


val Colors.inactiveIndicatorColor
    @Composable
    get() = if(isLight) lightGray else indicator

val Colors.activeIndicatorColor
    @Composable
    get() = if(isLight) active else Color.White