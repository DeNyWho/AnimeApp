package com.example.anibox.presenter.home.anime_popular.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.anibox.ui.theme.Grey
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerHorizontalChipIndicator(shimmerInstance: Shimmer) {
  Row(horizontalArrangement = Arrangement.Center) {
    Box(
      modifier = Modifier
        .width(120.dp)
        .height(8.dp)
        .clip(RoundedCornerShape(50f))
        .shimmer(shimmerInstance)
        .background(Grey),
    ) { }
  }
}