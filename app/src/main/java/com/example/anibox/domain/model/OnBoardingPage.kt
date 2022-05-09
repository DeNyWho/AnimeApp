package com.example.anibox.domain.model

import androidx.annotation.DrawableRes
import com.example.anibox.R

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
        firstImage = R.drawable.mangas,
        secondImage = R.drawable.catmanga,
        thirdImage = R.drawable.dragonmanga,
        fourthImage = R.drawable.moonmanga,
        fifthImage = R.drawable.tokyoghoulmanga,
        sixthImage = R.drawable.toradoramanga,
        seventhImage = R.drawable.voleyballmanga,
        eighthImage = R.drawable.yournamemanga
    )

    object Third : OnBoardingPage(
        title = "Explore!",
        description = "Find your favorite anime and watch or read them!",
        firstImage = R.drawable.mangas,
        secondImage = R.drawable.catmanga,
        thirdImage = R.drawable.dragonmanga,
        fourthImage = R.drawable.moonmanga,
        fifthImage = R.drawable.tokyoghoulmanga,
        sixthImage = R.drawable.toradoramanga,
        seventhImage = R.drawable.voleyballmanga,
        eighthImage = R.drawable.yournamemanga
    )
}

