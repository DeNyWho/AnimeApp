package com.example.animeapp.domain.model

import androidx.annotation.DrawableRes
import com.example.animeapp.R

sealed class OnBoardingPageSecond(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
)

sealed class OnBoardingPage(
    val title: String,
    val description: String,
    @DrawableRes
    val firstImage: Int,
    @DrawableRes
    val secondImage: Int,
    @DrawableRes
    val thirdImage: Int,
    @DrawableRes
    val fourthImage: Int,
    @DrawableRes
    val fifthImage: Int,
    @DrawableRes
    val sixthImage: Int,
    @DrawableRes
    val seventhImage: Int,
    @DrawableRes
    val eighthImage: Int
) {
    object First : OnBoardingPage(
        title = "Discover",
        description = "Discover the awesome\nworld of anime with our\nanime app.",
        firstImage = R.drawable.farfor,
        secondImage = R.drawable.tokyoone,
        thirdImage = R.drawable.name,
        fourthImage = R.drawable.titans,
        fifthImage = R.drawable.voleyball,
        sixthImage = R.drawable.toradora,
        seventhImage = R.drawable.drstone,
        eighthImage = R.drawable.hero
    )

    object Second : OnBoardingPage(
        title = "Explore!",
        description = "Find your favorite anime \nand watch or read them!",
        firstImage = R.drawable.mangas_min,
        secondImage = R.drawable.catmanga_min,
        thirdImage = R.drawable.dragonmanga_min,
        fourthImage = R.drawable.moonmanga_min,
        fifthImage = R.drawable.tokyoghoulmanga_min,
        sixthImage = R.drawable.toradoramanga_min,
        seventhImage = R.drawable.voleyballmanga_min,
        eighthImage = R.drawable.yournamemanga_min
    )

    object Third : OnBoardingPage(
        title = "Explore!",
        description = "Find your favorite anime and watch or read them!",
        firstImage = R.drawable.mangas_min,
        secondImage = R.drawable.catmanga_min,
        thirdImage = R.drawable.dragonmanga_min,
        fourthImage = R.drawable.moonmanga_min,
        fifthImage = R.drawable.tokyoghoulmanga_min,
        sixthImage = R.drawable.toradoramanga_min,
        seventhImage = R.drawable.voleyballmanga_min,
        eighthImage = R.drawable.yournamemanga_min
    )
}

