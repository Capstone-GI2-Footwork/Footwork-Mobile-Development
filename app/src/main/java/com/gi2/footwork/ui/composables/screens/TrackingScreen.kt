package com.gi2.footwork.ui.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.gi2.footwork.R
import com.gi2.footwork.ui.composables.atoms.BrandButton
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.destinationBackground
import com.gi2.footwork.ui.theme.destinationDivider
import com.gi2.footwork.ui.theme.destinationText
import com.gi2.footwork.ui.theme.locationCardSubtitle
import com.gi2.footwork.ui.theme.locationCardTitle
import com.gi2.footwork.ui.theme.locationOpen
import com.gi2.footwork.ui.theme.locationOpenBackground
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed
import com.gi2.footwork.ui.theme.suggestionBackground
import com.gi2.footwork.ui.theme.suggestionText
import com.gi2.footwork.ui.theme.trackingStarted
import com.gi2.footwork.ui.theme.unselectedNavigation

@Composable
//@Preview
fun TrackingSearchBar(
    onClick: () -> Unit = {  }
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(elevation = 12.dp,
                )
            .width(345.dp)
            .background(
                color = onPrimaryFixed,
                shape = RoundedCornerShape(size = 8.dp))
            .clickable { onClick() }
    ) {
        val text = "Search for location..."

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            BasicTextField(
                value = text,
                onValueChange = { /*TODO*/ },
                textStyle = AppTypography.bodyMedium.copy(
                    color = Color(0xFFBABABA)),
                modifier = Modifier.weight(1f),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
//@Preview
fun FootworkBottomBar() {
    var selectedItem by remember { mutableStateOf("Home") }

    @Composable
    fun NavigationItem(
        iconRes: Int,
        contentDescription: String,
        isSelected: Boolean,
        onClick: () -> Unit
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp),
                tint = if (isSelected) {
                    primaryFixed
                } else {
                    unselectedNavigation
                }
            )
        }
    }

    @Composable
    fun NavigationBar(
        selectedItem: String,
        onItemSelected: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .shadow(elevation = 12.dp)
                .background(color = onPrimaryFixed, shape = RoundedCornerShape(size = 16.dp))
                .padding(vertical = 4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationItem(
                    iconRes = R.drawable.ic_bottombar_home,
                    contentDescription = "Home",
                    isSelected = selectedItem == "Home",
                    onClick = { onItemSelected("Home") }
                )

                NavigationItem(
                    iconRes = R.drawable.ic_bottombar_leaderboard,
                    contentDescription = "Leaderboard",
                    isSelected = selectedItem == "Leaderboard",
                    onClick = { onItemSelected("Leaderboard") }
                )

                Spacer(modifier = Modifier.width(40.dp))

                NavigationItem(
                    iconRes = R.drawable.ic_bottombar_tracking,
                    contentDescription = "Tracking",
                    isSelected = selectedItem == "Tracking",
                    onClick = { onItemSelected("Tracking") }
                )

                NavigationItem(
                    iconRes = R.drawable.ic_bottombar_community,
                    contentDescription = "Community",
                    isSelected = selectedItem == "Community",
                    onClick = { onItemSelected("Community") }
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NavigationBar(
            selectedItem = selectedItem,
            onItemSelected = { newSelection ->
                selectedItem = newSelection

                /* TODO */
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .padding(bottom = 24.dp)
                .size(56.dp)
                .clip(CircleShape)
                .background(color = primaryFixed)
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bottombar_main),
                contentDescription = "Main Content",
                modifier = Modifier.size(24.dp),
                tint = onPrimaryFixed
            )
        }
    }
}

@Composable
//@Preview
fun SuggestionsContainer(
    suggestionList: List<Pair<String, String>> = listOf(
        Pair("Universitas 17 Agustus 1945 Surabaya", "50 m"),
        Pair("Universitas Negeri Surabaya", "100 m"),
        Pair("Universitas Pembangunan Nasional", "150 m"),
    )
) {
    @Composable
    fun SuggestionsItem(
        onClick: () -> Unit = {},
        name: String,
        distance: String
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_suggestions_pin),
                    contentDescription = "Pins",
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = name,
                    style = AppTypography.bodySmall.copy(
                        color = suggestionText
                    ),
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = distance,
                    style = AppTypography.bodySmall.copy(
                        color = suggestionText
                    )
                )
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = suggestionBackground,
                shape = RoundedCornerShape(
                    bottomEnd = 8.dp,
                    bottomStart = 8.dp
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            suggestionList.forEachIndexed { index, suggestion ->
                SuggestionsItem(
                    name = suggestion.first,
                    distance = suggestion.second,
                    onClick = {
                        /* TODO */
                    }
                )

                if (index < suggestionList.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFFBABABA)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
//@Preview
fun LocationCard(
    imageResource: Int = R.drawable.img_untag,
    name: String = "Universitas 17 Agustus 1945 Surabaya",
    address: String = "Jl. Semolowaru No.45, Menur Pumpungan, Kec. Sekolilo"
) {
    Box(
        modifier = Modifier
            .width(393.dp)
            .height(396.dp)
            .background(color = onPrimaryFixed, shape = RoundedCornerShape(size = 24.dp))
    ){
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    top = 16.dp,
                    end = 24.dp,
                    bottom = 24.dp
                )
        ){
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Location Card",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(345.dp)
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(size = 8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = name,
                style = AppTypography.bodyMedium.copy(
                    fontWeight = FontWeight(600),
                    color = locationCardTitle
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = address,
                style = AppTypography.bodySmall.copy(
                    fontWeight = FontWeight(400),
                    color = locationCardSubtitle
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .border(width = 1.dp, color = locationOpen, shape = RoundedCornerShape(size = 25.dp))
                        .width(55.dp)
                        .height(22.dp)
                        .background(color = locationOpenBackground, shape = RoundedCornerShape(size = 25.dp))
                        .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 4.dp)
                ){
                    Text(
                        text = "Open",
                        style = AppTypography.bodySmall.copy(
                            fontWeight = FontWeight(400),
                            color = locationOpen
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Close 10PM",
                    style = AppTypography.bodySmall.copy(
                        fontWeight = FontWeight(400),
                        color = locationCardSubtitle
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            BrandButton(
                text = "Button",
                onClick = {  },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
//@Preview
fun DestinationCard(
    startLocation: String = "Your Location",
    terminalLocation: String = "Universitas 17 Agustus 1945 Surabaya",
    vehicleUsed: String = "Car",
    durationList: List<Map<String, String>> = listOf(
        mapOf("Car" to "15 min"),
        mapOf("Motorcycle" to "10 min"),
        mapOf("EV Cycle" to "20 min"),
        mapOf("Footprints" to "30 min")
    )
){
    @Composable
    fun DestinationFieldContainer(){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = destinationBackground,
                    shape = RoundedCornerShape(size = 8.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_start_destination),
                        contentDescription = "Start Location",
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = startLocation,

                        style = AppTypography.bodyMedium.copy(
                            fontWeight = FontWeight(400),
                            color = destinationText
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = destinationDivider,
                        modifier = Modifier.weight(1f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_connected_destination),
                        contentDescription = "Connected Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_terminal_destination),
                        contentDescription = "Start Location",
                        modifier = Modifier.size(32.dp)
                    )

                    Text(
                        text = terminalLocation,

                        style = AppTypography.bodyMedium.copy(
                            fontWeight = FontWeight(400),
                            color = destinationText
                        ),

                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }

    @Composable
    fun VehicleUsedButton(
        isFocus: Boolean = false,
        imageResource: Int = R.drawable.ic_motorcycle,
        duration: String = "15 min"
    ){
        val backgroundColor = if (isFocus) primaryFixed else onPrimaryFixed
        val iconColor = if (isFocus) onPrimaryFixed else primaryFixed

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(width = 1.dp, color = primaryFixed, shape = RoundedCornerShape(size = 8.dp))
                .size(74.dp)
                .background(color = backgroundColor, shape = RoundedCornerShape(size = 8.dp))
                .padding(16.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = imageResource),
                    contentDescription = "Vehicle Icon",
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = duration,

                    style = AppTypography.bodySmall.copy(
                        fontWeight = FontWeight(500),
                        color = iconColor
                    )
                )
            }
        }
    }


    Box(
        modifier = Modifier
            .width(393.dp)
            .height(334.dp)
            .background(color = onPrimaryFixed, shape = RoundedCornerShape(size = 24.dp))
            .padding(24.dp)
    ) {
        Column {
            DestinationFieldContainer()

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                VehicleUsedButton(
                    isFocus = vehicleUsed == "Car",
                    imageResource = R.drawable.ic_car,
                    duration = durationList.find { it.containsKey("Car") }?.get("Car") ?: "15 min"
                )
                VehicleUsedButton(
                    isFocus = vehicleUsed == "Motorcycle",
                    imageResource = R.drawable.ic_motorcycle,
                    duration = durationList.find { it.containsKey("Motorcycle") }?.get("Motorcycle") ?: "10 min"
                )
                VehicleUsedButton(
                    isFocus = vehicleUsed == "EV Cycle",
                    imageResource = R.drawable.ic_ev_cycle,
                    duration = durationList.find { it.containsKey("EV Cycle") }?.get("EV Cycle") ?: "20 min"
                )
                VehicleUsedButton(
                    isFocus = vehicleUsed == "Footprints",
                    imageResource = R.drawable.ic_footprints,
                    duration = durationList.find { it.containsKey("Footprints") }?.get("Footprints") ?: "30 min"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            BrandButton(
                text = "Button",
                onClick = {  },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
//@Preview
fun TrackingStartedTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart) // Align the Row (arrow) to the start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text = "Track Your Destination",
            style = AppTypography.titleMedium.copy(
                fontWeight = FontWeight(600),
                color = trackingStarted
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
//@Preview
fun TrackingStartedCard(
    duration: String = "15 mnt",
    distance: String = "500 m",
    emission: String = "50"
){
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(88.dp)
    ){
        Box(
            modifier = Modifier
                .width(32.dp)
                .height(8.dp)
                .background(color = onPrimaryFixed, shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .align(Alignment.TopCenter)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_slide_up),
            contentDescription = "Tracking Started",
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.TopCenter)
                .zIndex(1f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = onPrimaryFixed, shape = RoundedCornerShape(size = 24.dp))
                .padding(vertical = 16.dp, horizontal = 24.dp)
                .align(Alignment.BottomCenter)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_cancel_tracking),
                        contentDescription = "Cancel Tracking",
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = duration,

                        style = AppTypography.bodyLarge.copy(
                            fontWeight = FontWeight(600),
                            color = trackingStarted
                        )
                    )

                    Text(
                        text = "$distance • $emission CO²g",


                        style = AppTypography.bodyMedium.copy(
                            fontWeight = FontWeight(400),
                            color = Color.Black
                        )
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_tracking_calibrate),
                        contentDescription = "Calibrate Tracking",
                    )
                }
            }
        }
    }
}

@Composable
//@Preview
fun TrackingContent(
    isSuggestionsVisible: Boolean = false,
    isBottomBarHide: Boolean = false,
    isLocationCardVisible: Boolean = false,
    isDestinationCardVisible: Boolean = false,
    isTrackingStarted: Boolean = false,
){
    val rememberIsSuggestionsVisible = remember { mutableStateOf(isSuggestionsVisible) }
    val rememberIsBottomBarHide = remember { mutableStateOf(isBottomBarHide) }
    val rememberIsLocationCardVisible = remember { mutableStateOf(isLocationCardVisible) }
    val rememberIsDestinationCardVisible = remember { mutableStateOf(isDestinationCardVisible) }
    val rememberIsTrackingStarted = remember { mutableStateOf(isTrackingStarted) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.bg_tmp_tracking),
                contentScale = ContentScale.FillHeight
            )
    ){
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                if(rememberIsTrackingStarted.value){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(144.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(onPrimaryFixed, Color.Transparent),
                                    startY = 0f,
                                    endY = Float.POSITIVE_INFINITY
                                )
                            )
                            .padding(horizontal = 24.dp)
                    ){
                        TrackingStartedTopBar()
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 56.dp, start = 24.dp, end = 24.dp)
                    ) {
                        TrackingSearchBar(onClick = {
                            rememberIsSuggestionsVisible.value = true
                        })

                        if (rememberIsSuggestionsVisible.value) {
                            SuggestionsContainer()
                        }

                    }
                }
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
                ){
                    if(!rememberIsBottomBarHide.value){
                        FootworkBottomBar()
                    }

                    if(rememberIsTrackingStarted.value){
                        TrackingStartedCard()
                    }
                }

                if(rememberIsLocationCardVisible.value){
                    LocationCard()
                }

                if(rememberIsDestinationCardVisible.value){
                    DestinationCard()
                }
            }
        ){ innerPadding ->
            /* TODO */
        }
    }
}

@Composable
@Preview(showSystemUi = true, apiLevel = 30)
fun TrackingBaseScreen(){
    TrackingContent()
}

@Composable
@Preview(showSystemUi = true, apiLevel = 30)
fun TrackingSearchingScreen(){
    TrackingContent(
        isSuggestionsVisible = true
    )
}

@Composable
@Preview(showSystemUi = true, apiLevel = 30)
fun TrackingLocationDetailScreen(){
    TrackingContent(
        isLocationCardVisible = true
    )
}

@Composable
@Preview(showSystemUi = true, apiLevel = 30)
fun TrackingPreStartScreen(){
    TrackingContent(
        isDestinationCardVisible = true
    )
}

@Composable
@Preview(showSystemUi = true, apiLevel = 30)
fun TrackingStartedScreen(){
    TrackingContent(
        isBottomBarHide = true,
        isTrackingStarted = true
    )
}