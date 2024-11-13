package com.gi2.footwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gi2.footwork.presentation.ui.composables.FootworkNavigation
import com.gi2.footwork.presentation.ui.theme.FootworkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      FootworkTheme {
        FootworkNavigation()
      }
    }
  }
}