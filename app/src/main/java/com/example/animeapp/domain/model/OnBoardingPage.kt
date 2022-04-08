package com.example.animeapp.domain.model

import androidx.annotation.DrawableRes
import com.example.animeapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.farfor,
        title = "Hello!",
        description = "Browse a list of anime\nhappening tonight, tomorrow,\nand the next day."
    )

    object Second : OnBoardingPage(
        image = R.drawable.farfor,
        title = "Explore!",
        description = "Find your favorite anime and watch or read them!"
    )

    object Third : OnBoardingPage(
        image = R.drawable.petgirl,
        title = "Share!",
        description = "Share your favorite anime with your friends, add to your personal list!"
    )
}

