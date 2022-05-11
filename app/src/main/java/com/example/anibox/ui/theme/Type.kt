package com.example.anibox.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.anibox.R

// Set of Material typography styles to start with

val monster = FontFamily(
    Font(R.font.monster_regular, weight = FontWeight.Normal),
    Font(R.font.monster_black, weight = FontWeight.Black),
    Font(R.font.monster_medium, weight = FontWeight.Medium),
    Font(R.font.monster_semibold, weight = FontWeight.SemiBold),
    Font(R.font.monster_thin, weight = FontWeight.Thin),
    Font(R.font.monster_extralight, weight = FontWeight.ExtraLight)
)

val nunito = FontFamily(
    Font(R.font.nunito_black, weight = FontWeight.Black),
    Font(R.font.nunito_regular, weight = FontWeight.Normal),
    Font(R.font.nunito_semibold, weight = FontWeight.SemiBold)
)


val nunitoType = Typography(

    h1 = TextStyle(
        fontFamily = nunito,
        fontWeight = FontWeight.SemiBold,
        fontSize = 19.sp
    )
)


val monsterTypo = Typography(

    h1 = TextStyle(
        fontFamily = monster,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    )
)


val Typography = Typography(

    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),

    h1 = TextStyle(
        fontFamily = monster,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),

    h2 = TextStyle(
        fontFamily = monster,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    h3 = TextStyle(
        fontFamily = monster,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    h4 = TextStyle(
        fontFamily = monster,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),

    h5 = TextStyle(
        fontFamily = monster,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)