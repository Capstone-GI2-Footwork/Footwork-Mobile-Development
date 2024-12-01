package com.gi2.footwork.features.Community.presentation.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.features.Community.model.UserPostsItemModel
import com.gi2.footwork.features.Community.model.UserStoriesItemModel
import com.gi2.footwork.features.Community.model.userPostsList
import com.gi2.footwork.features.Community.model.userStoryList
import com.gi2.footwork.features.Profile.presentation.screen.ProfileAvatarImage
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.blueVerticalGradient
import com.gi2.footwork.ui.theme.homeSectionTitle
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.silverVerticalGradient
import com.gi2.footwork.ui.theme.userGreetingSubText

@Composable
fun UserStoryItem(
    @DrawableRes sourceImage: Int = R.drawable.sample_avatar_1,
    username: String = "JeromeCooper",
    onClick: () -> Unit = {},
    isViewed: Boolean = false,
    modifier: Modifier = Modifier
        .height(104.dp)
){
    val usernameToBeDisplayed = if (username.length > 10) {
        "${username.substring(0, 6)}..."
    } else {
        username
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = CircleShape)
                .border(
                    width = 2.dp,
                    brush = if (isViewed) {
                        silverVerticalGradient
                    } else {
                        blueVerticalGradient
                    },
                    shape = CircleShape
                )
                .clickable { onClick() }
        ){
            Image(
                painter = painterResource(id = sourceImage),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .clip(shape = CircleShape)
            )
        }

        Text(
            text = usernameToBeDisplayed,
            style = AppTypography.bodyMedium,
            color = homeSectionTitle,
            maxLines = 1,
        )
    }
}

@Composable
//@Preview
private fun UserStoryCarousel(
    userStories: List<UserStoriesItemModel> = userStoryList,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.horizontalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ProfileAvatarImage(
                avatar = R.drawable.user_avatar,
                onClick = {}
            )

            Text(
                text = "Add",
                style = AppTypography.bodyMedium,
                color = homeSectionTitle,
                maxLines = 1,
            )
        }

         userStories.forEach { userStory ->
            UserStoryItem(
                sourceImage = userStory.sourceImage,
                username = userStory.username,
                isViewed = userStory.isViewed,
                onClick = userStory.onClick,
                modifier = modifier
            )
        }
    }
}

@Composable
//@Preview
fun UserPostCard(
    @DrawableRes sourceImage: Int = R.drawable.sample_post_1,
    userAvatar: Int = R.drawable.sample_avatar_5,
    username: String = "Marvin McKinney",
    location: String = "Surabaya, Indonesia",
    time: String = "08:15 WIB",
    likeCount: Int = 6,
    commentCount: Int = 3,
    description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec odio nec nisl luctus lacinia. Nullam nec nisl nec",
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .background(
                color = onPrimaryFixed,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = userAvatar),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape)
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = username,
                    style = AppTypography.titleMedium.copy(
                        fontWeight = FontWeight(600)
                    ),
                    color = homeSectionTitle,
                    maxLines = 1,
                )

                Text(
                    text = "$location | $time",
                    style = AppTypography.bodySmall.copy(
                        fontWeight = FontWeight(400)
                    ),
                    color = userGreetingSubText,
                    maxLines = 1,
                )
            }
        }

        Image(
            painter = painterResource(id = sourceImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(328.dp)
                .clip(shape = RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

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

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = homeSectionTitle
                    )
                ) {
                    append(username)
                }
                append(" ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        color = homeSectionTitle
                    )
                ) {
                    append(description)
                }
            },
            style = AppTypography.bodyMedium,
            maxLines = 2
        )
    }
}

@Composable
@Preview(
    showSystemUi = true,
)
fun CommunityScreen(
    userStories: List<UserStoriesItemModel> = userStoryList,
    userPosts: List<UserPostsItemModel> = userPostsList,
) {
    Scaffold(
        containerColor = onPrimaryFixed
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            UserStoryCarousel(
                userStories = userStories,
                modifier = Modifier.padding(start = 16.dp)
            )

            userPosts.forEach { userPost ->
                UserPostCard(
                    sourceImage = userPost.sourceImage,
                    userAvatar = userPost.userAvatar,
                    username = userPost.username,
                    location = userPost.location,
                    time = userPost.time,
                    likeCount = userPost.likeCount,
                    commentCount = userPost.commentCount,
                    description = userPost.description,
                    onClick = userPost.onClick,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                )
            }
        }
    }
}
