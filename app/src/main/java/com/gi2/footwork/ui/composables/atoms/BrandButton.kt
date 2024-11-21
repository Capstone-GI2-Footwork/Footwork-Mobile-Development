package com.gi2.footwork.ui.composables.atoms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.ui.theme.*

@Composable
fun BrandButton(
  modifier: Modifier = Modifier,
  text: String,
  onClick: () -> Unit,
  leadingContent: @Composable () -> Unit = {},
  trailingContent: @Composable () -> Unit = {},
) {
  Button(
    modifier = modifier,
    onClick = onClick,
    shape = MaterialTheme.shapes.small,
    colors = ButtonDefaults.buttonColors().copy(
      containerColor = primaryFixed,
      contentColor = onPrimaryFixed,
    )
  ) {
    leadingContent()
    Text(text)
    trailingContent()
  }
}

@Preview(showBackground = true)
@Composable
private fun BrandButtonPreview() {
  FootworkTheme {
    Box(modifier = Modifier.padding(16.dp)) {
      BrandButton(text = "Click me!", onClick = {})
    }
  }
}