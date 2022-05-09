package com.example.anibox.presenter.home.view_holder

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
fun ItemAnimeTopShimmer(
    shimmerInstance: Shimmer
) {
    Column(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .width(160.dp)
            .padding(12.dp, 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Grey)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .background(color = Grey)
        )

        Box(
            modifier = Modifier
                .width(62.dp)
                .height(18.dp)
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .background(color = Grey)
        )

        Box(
            modifier = Modifier
                .width(32.dp)
                .height(18.dp)
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .background(color = Grey)
        )

    }
}