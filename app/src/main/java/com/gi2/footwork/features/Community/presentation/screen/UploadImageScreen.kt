package com.gi2.footwork.features.Community.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.homeSectionTitle
import com.gi2.footwork.ui.theme.onPrimaryFixed

@Composable
@Preview(
    showSystemUi = true,
)
fun UploadImageScreen(
    onClickSend: () -> Unit = {}
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sample_upload_1),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(16.dp)
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
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
                    .background(
                        color = onPrimaryFixed.copy(
                            alpha = 0.5f
                        ),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = onPrimaryFixed.copy(
                            alpha = 0.7f
                        ),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .align(Alignment.BottomCenter)
            ){
                BasicTextField(
                    value = "",
                    onValueChange = {},
                    textStyle = AppTypography.bodyMedium
                        .copy(color = homeSectionTitle),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    decorationBox = { innerTextField ->
                        if ("".isEmpty()) {
                            Text(
                                text = "Enter description",
                                style = AppTypography.bodyMedium.copy(
                                    color = onPrimaryFixed
                                )
                            )
                        }
                        innerTextField()
                    }
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_send_description),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(20.dp)
                        .clickable { onClickSend() }
                )
            }
        }
    }
}