package com.gi2.footwork.features.Community.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.gi2.footwork.R
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.onPrimaryFixed

@Composable
@Preview(
    showSystemUi = true,
)
fun ImagePickerScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(500.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.sample_upload_1),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .background(
                                color = onPrimaryFixed.copy(
                                    alpha = 0.3f
                                ),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = null,
                            tint = onPrimaryFixed
                        )

                        Text(
                            text = "New Post",
                            style = AppTypography.titleMedium,
                            color = onPrimaryFixed
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                color = onPrimaryFixed.copy(
                                    alpha = 0.3f
                                ),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Next",
                            style = AppTypography.titleMedium,
                            color = onPrimaryFixed
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .size(40.dp)
                        .background(
                            color = onPrimaryFixed.copy(
                                alpha = 0.3f
                            ),
                            shape = CircleShape
                        )
                        .padding(5.dp)
                        .background(
                            color = onPrimaryFixed,
                            shape = CircleShape
                        )
                        .align(Alignment.BottomCenter)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                            .align(Alignment.Center),
                        tint = Color(0xFF4F4F4F)
                    )
                }
            }

            val imageResources = (2..17).map { index ->
                val resourceName = "sample_upload_$index"
                val resourceId = LocalContext.current.resources.getIdentifier(
                    resourceName,
                    "drawable",
                    LocalContext.current.packageName
                )
                resourceId
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(imageResources.size) { index ->
                    Image(
                        painter = painterResource(id = imageResources[index]),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}