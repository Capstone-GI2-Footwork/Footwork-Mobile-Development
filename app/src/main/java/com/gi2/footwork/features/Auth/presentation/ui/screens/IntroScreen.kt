package com.gi2.footwork.features.Auth.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.ui.composables.atoms.BrandButton
import com.gi2.footwork.ui.composables.atoms.SearchableExpandedDropDownMenu
import com.gi2.footwork.ui.theme.FootworkTheme

@Composable
fun IntroScreen(
  modifier: Modifier = Modifier,
  onFormSubmit: () -> Unit,
  onResultNext: () -> Unit,
) {
  var currentScreen by remember { mutableStateOf("intro") }

  if (currentScreen == "intro") {
    IntroScreenContent(
      modifier = modifier,
      onSubmit = {
        currentScreen = "result"
        onFormSubmit()
      }
    )
  } else {
    EmissionResultScreen(
      modifier = modifier,
      onNext = onResultNext
    )
  }
}

@Composable
private fun IntroScreenContent(
  modifier: Modifier = Modifier,
  onSubmit: () -> Unit,
) {
  Scaffold(
    modifier = modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.background
          )
        )
      ),
    bottomBar = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        BrandButton(
          text = "Calculate Emission",
          modifier = Modifier.fillMaxWidth(),
          onClick = onSubmit
        )
      }
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
      Box(
        modifier = Modifier
          .fillMaxHeight(0.4f)
          .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
      ) {
        Image(
          painterResource(R.drawable.img_illustration_intro),
          contentDescription = null,
          modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
        )
      }
      Column(
        modifier = Modifier
          .fillMaxHeight(1f)
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
          .background(MaterialTheme.colorScheme.background)
          .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        FormInputs(
          modifier = Modifier.fillMaxWidth()
        )
      }
    }
  }
}

@Composable
private fun FormInputs(
  modifier: Modifier = Modifier,
) {
  val keyboardController = LocalSoftwareKeyboardController.current

  val transports = remember {
    mutableStateListOf<String>(
      "Walk",
      "Car",
      "Motorcycle",
      "EV Cycle",
      "EV Car"
    )
  }
  var selectedTransport by remember { mutableStateOf<String?>(null) }
  val vehicles = remember {
    mutableStateListOf<String>(
      "Honda CRF150L",
      "Honda CRF250L",
      "Honda Beat Street CBS",
      "Honda ADV 160 ABS",
      "Honda ADV 160 CBS",
    )
  }
  var selectedVehicle by remember { mutableStateOf<String?>(null) }
  val fuels = remember {
    mutableStateListOf<String>(
      "Pertalite",
      "Pertamax",
      "Pertamax Green",
      "Pertamax Turbo",
      "Shell Super"
    )
  }
  var selectedFuel by remember { mutableStateOf<String?>(null) }

  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    SearchableExpandedDropDownMenu(
      listOfItems = transports,
      modifier = Modifier.fillMaxWidth(),
      placeholder = { Text("Select your transportation...") },
      onDropDownItemSelected = { selectedTransport = it },
      dropdownItem = { item ->
        ListItem(
          modifier = Modifier.fillMaxWidth(),
          headlineContent = {
            Text(
              item,
              style = MaterialTheme.typography.bodyLarge
            )
          },
        )
      },
      defaultItem = {
        Log.e("DEFAULT_ITEM", it)
      },
      onSearchTextFieldClicked = {
        keyboardController?.show()
      }
    )
    SearchableExpandedDropDownMenu(
      listOfItems = vehicles,
      modifier = Modifier.fillMaxWidth(),
      placeholder = { Text("Select your vehicle...") },
      onDropDownItemSelected = { selectedVehicle = it },
      dropdownItem = { item ->
        ListItem(
          modifier = Modifier.fillMaxWidth(),
          headlineContent = {
            Text(
              item,
              style = MaterialTheme.typography.bodyLarge
            )
          },
        )
      },
      defaultItem = {
        Log.e("DEFAULT_ITEM", it)
      },
      onSearchTextFieldClicked = {
        keyboardController?.show()
      }
    )
    SearchableExpandedDropDownMenu(
      listOfItems = fuels,
      modifier = Modifier.fillMaxWidth(),
      placeholder = { Text("Select your fuel type...") },
      onDropDownItemSelected = { selectedFuel = it },
      dropdownItem = { item ->
        ListItem(
          modifier = Modifier.fillMaxWidth(),
          headlineContent = {
            Text(
              item,
              style = MaterialTheme.typography.bodyLarge
            )
          },
        )
      },
      defaultItem = {
        Log.e("DEFAULT_ITEM", it)
      },
      onSearchTextFieldClicked = {
        keyboardController?.show()
      }
    )
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun IntroScreenPreview() {
  FootworkTheme {
    IntroScreen(
      onFormSubmit = { },
      onResultNext = { }
    )
  }
}
