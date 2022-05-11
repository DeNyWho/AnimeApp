package com.example.anibox.presentation.home.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anibox.ui.theme.Grey

@Composable
fun SearchEditText(
    fieldPlaceholder: String = ""
) {
    Text(
        text = fieldPlaceholder,
        style = TextStyle(color = Grey, fontSize = 16.sp)
    )
}

@Composable
fun SearchLeadingIcon(
    size: Dp = 24.dp,
    padding: PaddingValues = PaddingValues(6.dp)
) {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search",
        modifier = Modifier.padding(paddingValues = padding),
        tint = Grey
    )
}

//@Composable
//fun SearchTrailingIcon(
//    size: Dp = 24.dp,
//    padding: PaddingValues = PaddingValues(6.dp),
//    onClick: () -> Unit
//) {
//
//    IconButton(
//        onClick = onClick,
//        modifier = Modifier.then(Modifier.size(size))
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.ic_close),
//            contentDescription = "Close",
//            tint = Grey
//        )
//    }
//
//}