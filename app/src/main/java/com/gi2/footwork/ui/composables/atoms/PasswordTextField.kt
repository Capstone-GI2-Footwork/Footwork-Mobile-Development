package com.gi2.footwork.ui.composables.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.ui.theme.FootworkTheme

@Composable
fun PasswordTextField(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  enabled: Boolean = true,
  error: String,
  onDone: () -> Unit = {},
) {
  var isObscure by remember { mutableStateOf(true) }
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
      label = { Text("Password") },
      placeholder = { Text("Enter your password.") },
      enabled = enabled,
      visualTransformation = if (isObscure) PasswordVisualTransformation() else VisualTransformation.None,
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(
        onDone = {
          keyboardController?.hide()
          onDone()
        }
      ),
      isError = error.isNotEmpty(),
      trailingIcon = {
        val imageVector =
          if (isObscure) Icons.Outlined.VisibilityOff
          else Icons.Outlined.Visibility
        val description = if (isObscure) "Show password" else "Hide password"

        IconButton(
          onClick = { isObscure = !isObscure },
        ) {
          Icon(imageVector, description)
        }
      },
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
private fun PasswordTextFieldPreview() {
  FootworkTheme {
    Box(
      modifier = Modifier.padding(16.dp)
    ) {
      PasswordTextField(
        value = "",
        onValueChange = {},
        error = "Password must be at least 8 characters."
      )
    }
  }
}