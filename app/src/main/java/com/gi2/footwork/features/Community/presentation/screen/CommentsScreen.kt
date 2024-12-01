package com.gi2.footwork.features.Community.presentation.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.features.Community.model.UserCommentsItemModel
import com.gi2.footwork.features.Community.model.userCommentsList
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.homeSectionTitle
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.userGreetingSubText

@Composable
//@Preview
fun CommentsItem(
    @DrawableRes userAvatar: Int = R.drawable.sample_avatar_6,
    username: String = "Name",
    time: String = "1 min",
    comment: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    likeCount: Int = 6,
    commentCount: Int = 3,
    modifier: Modifier = Modifier,
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ){
        Image(
            painter = painterResource(id = userAvatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
                .clip(shape = CircleShape)
                .align(Alignment.Top)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = username,
                    style = AppTypography.labelLarge
                        .copy(color = homeSectionTitle)
                )
                Text(
                    text = time,
                    style = AppTypography.labelMedium
                        .copy(color = userGreetingSubText),
                )
            }

            Text(
                text = comment,
                style = AppTypography.bodyMedium
                    .copy(fontWeight = FontWeight.Normal,
                        color = Color(0xFF404040)
                    )
            )

            Spacer(modifier = Modifier.size(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = "$likeCount Suka",
                        style = AppTypography.bodyMedium,
                        color = homeSectionTitle,
                        maxLines = 1,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = "$commentCount Komentar",
                        style = AppTypography.bodyMedium,
                        color = homeSectionTitle,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}

@Composable
//@Preview
fun CommentsCardBottomBar(
    userAvatar: Int = R.drawable.sample_avatar_6,
    onClickSend: () -> Unit = {},
    modifier: Modifier = Modifier,
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .background(
                color = onPrimaryFixed.copy(
                    alpha = 0.7f
                )
            )
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            )
    ){
        Image(
            painter = painterResource(id = userAvatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
                .clip(shape = CircleShape)
                .align(Alignment.Top)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFEDEDED),
                    shape = RoundedCornerShape(16.dp)
                )
        ){
            BasicTextField(
                value = "",
                onValueChange = {},
                textStyle = AppTypography.bodyMedium
                    .copy(color = homeSectionTitle),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                decorationBox = { innerTextField ->
                    if ("".isEmpty()) {
                        Text(
                            text = "add comments...",
                            style = AppTypography.bodyMedium.copy(
                                color = Color.Gray
                            )
                        )
                    }
                    innerTextField()
                }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(20.dp)
                    .clickable { onClickSend() }
            )
        }
    }

}

@Composable
@Preview
fun CommentsCard(
    comments: List<UserCommentsItemModel> = userCommentsList,
    modifier: Modifier = Modifier,
) {
    val totalComments = comments.size

    Box(
        modifier = modifier
            .background(
                color = onPrimaryFixed,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                )
            )
            .height(400.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Column {
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(16.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "$totalComments Komentar",
                    style = AppTypography.labelMedium
                        .copy(color = homeSectionTitle),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                comments.forEach {
                    CommentsItem(
                        userAvatar = it.userAvatar,
                        username = it.username,
                        time = it.time,
                        comment = it.comment,
                        likeCount = it.likeCount,
                        commentCount = it.commentCount,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        CommentsCardBottomBar(
            userAvatar = R.drawable.sample_avatar_6,
            onClickSend = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}
