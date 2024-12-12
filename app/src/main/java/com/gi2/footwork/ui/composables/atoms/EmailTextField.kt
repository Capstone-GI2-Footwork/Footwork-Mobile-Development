package com.gi2.footwork.ui.composables.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.ui.theme.FootworkTheme

@Composable
fun EmailTextField(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  enabled: Boolean = true,
  error: String,
  onDone: () -> Unit = {},
) {
  val keyboardController = LocalSoftwareKeyboardController.current

  Column(
    modifier = Modifier.fillMaxWidth(),
  ) {
    OutlinedTextField(
      modifier = modifier.fillMaxWidth(),
      value = value,
      onValueChange = {
        onValueChange(it)
      },
      label = { Text("Email Address") },
      placeholder = { Text("Enter your email address.") },
      enabled = enabled,
      isError = error.isNotEmpty(),
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next
      ),
      keyboardActions = KeyboardActions(
        onDone = {
          keyboardController?.hide()
          onDone()
        }
      ),
      singleLine = true,
      maxLines = 1,
      shape = MaterialTheme.shapes.small
    )
    if (error.isNotEmpty()) {
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = error,
        style = MaterialTheme.typography
          .bodySmall
          .copy(color = MaterialTheme.colorScheme.error),
        modifier = Modifier.padding(start = 16.dp)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview() {
  FootworkTheme {
    Box(
      modifier = Modifier.padding(16.dp),
    ) {
      EmailTextField(
        value = "",
        onValueChange = {},
        error = "Must be a valid email address (e.g. john@acme.inc).",
      )
    }
  }
}