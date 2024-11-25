package com.gi2.footwork.ui.composables.atoms

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle


@Composable
fun InteractiveText(
  modifier: Modifier = Modifier,
  text: String,
  clickableText: String,
  onClick: () -> Unit,
) {
  val annotatedString = buildAnnotatedString {
    withStyle(
      style = MaterialTheme.typography
        .bodySmall
        .copy(MaterialTheme.colorScheme.onSurfaceVariant)
        .toSpanStyle()
    ) {
      append(text)
    }
    withStyle(
      style = MaterialTheme.typography
        .bodySmall
        .copy(
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.W500,
        )
        .toSpanStyle()
    ) {
      append(clickableText)
      addStringAnnotation(
        tag = "Clickable",
        start = 0,
        end = clickableText.length,
        annotation = "Clickable"
      )
    }
  }

  @Suppress("DEPRECATION")
  (ClickableText(
    annotatedString,
    modifier = modifier,
    style = MaterialTheme.typography.bodySmall,
    onClick = {
      annotatedString.getStringAnnotations("Clickable", 0, clickableText.length)
        .firstOrNull()
        ?.let { onClick() }
    }
  ))
}
