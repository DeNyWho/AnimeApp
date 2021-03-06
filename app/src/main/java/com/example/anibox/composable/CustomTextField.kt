package com.example.anibox.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.anibox.ui.theme.BlackLighterBackground
import com.example.anibox.ui.theme.smokyWhite
import com.example.anibox.presentation.home.composable.SearchEditText

@Composable
fun CustomTextField(
  modifier: Modifier = Modifier,
  elevation: Dp = 0.dp,
  padding: PaddingValues = PaddingValues(0.dp),
  content: (@Composable () -> Unit)? = null,
  leadingIcon: (@Composable () -> Unit)? = null,
  trailingIcon: (@Composable () -> Unit)? = null
) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(8.dp),
    color = if (isSystemInDarkTheme()) BlackLighterBackground else smokyWhite,
    elevation = elevation
  ) {
    Row(
      modifier = Modifier.padding(padding),
      verticalAlignment = Alignment.CenterVertically
    ) {
      if (leadingIcon != null) {
        leadingIcon()
      }

      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        if (content != null) {
          content()
        }
      }


      if (trailingIcon != null) {
        trailingIcon()
      }
    }
  }
}

@Preview(
  widthDp = 280
)
@Composable
fun CustomTextFieldPreview() {
  CustomTextField(
    content = { SearchEditText() },
    leadingIcon = {
      Icon(imageVector = Icons.Default.Call, contentDescription = "")
    },
    trailingIcon = {
      Icon(imageVector = Icons.Default.Home, contentDescription = "")
    },
    padding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
  )
}
