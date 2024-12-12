package com.gi2.footwork.ui.composables.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.ui.theme.FootworkTheme

@Composable
fun GoogleSignInButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  OutlinedButton(
    modifier = modifier,
    onClick = onClick,
    shape = MaterialTheme.shapes.small,
    enabled = false
  ) {
    Image(
      painterResource(R.drawable.ic_google),
      contentDescription = "Google Icon",
      modifier = Modifier
        .size(16.dp)
        .alpha(0.7f)
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text("Sign in with Google")
  }
}

@Preview(showBackground = true)
@Composable
private fun GoogleSignInButtonPreview() {
  FootworkTheme {
    Box(
      modifier = Modifier.padding(16.dp)
    ) {
      GoogleSignInButton {}
    }
  }
}