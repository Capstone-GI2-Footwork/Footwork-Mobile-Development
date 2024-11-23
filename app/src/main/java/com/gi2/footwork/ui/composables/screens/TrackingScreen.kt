package com.gi2.footwork.ui.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.gi2.footwork.R
import com.gi2.footwork.ui.composables.atoms.BrandButton

@Composable
//@Preview
fun SearchBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(elevation = 12.dp, spotColor = Color(0x14000000), ambientColor = Color(0x14000000))
            .width(345.dp)
            .background(
                color = Color(0xFFFAFAFA),
                shape = RoundedCornerShape(size = 8.dp))
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
                onValueChange = {
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFBABABA),
                ),
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
fun FootworkBottomNavBarElement(
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(elevation = 12.dp, spotColor = Color(0x1F000000), ambientColor = Color(0x1F000000))
            .background(color = Color(0xFFFAFAFA), shape = RoundedCornerShape(size = 16.dp))
            .padding(vertical = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bottombar_home),
                contentDescription = "Home",
                modifier = Modifier.size(24.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_bottombar_leaderboard),
                contentDescription = "Search",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(24.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_bottombar_tracking),
                contentDescription = "Location",
                modifier = Modifier.size(24.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_bottombar_community),
                contentDescription = "Profile",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
//@Preview
fun FootworkBottomBar(

){
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        FootworkBottomNavBarElement(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(bottom = 24.dp)
                .size(56.dp)
                .clip(CircleShape)
                .background(color = Color(0xFF52B040))
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bottombar_main),
                contentDescription = "Main Content",
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

@Composable
//@Preview
fun SearchSuggestionsItem(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
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
                text = "Universitas 17 Agustus 1945 Surabaya",

                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF7B7B7B),
                ),

                modifier = Modifier.weight(1f)
            )

            Text(
                text = "50 m",

                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF7B7B7B),
                )
            )
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun SuggestionsContainer(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0x80FAFAFA),
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
            SearchSuggestionsItem()

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFBABABA)
            )

            Spacer(modifier = Modifier.height(8.dp))

            SearchSuggestionsItem()

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFBABABA)
            )

            Spacer(modifier = Modifier.height(8.dp))

            SearchSuggestionsItem()
        }
    }
}

@Composable
//@Preview
fun LocationCard() {
    Box(
        modifier = Modifier
            .width(393.dp)
            .height(396.dp)
            .background(color = Color(0xFFFAFAFA), shape = RoundedCornerShape(size = 24.dp))
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
                painter = painterResource(id = R.drawable.img_untag),
                contentDescription = "Location Card",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(345.dp)
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(size = 8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Universitas 17 Agustus 1945 Surabaya",

                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF404040),
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Jl. Semolowaru No.45, Menur Pumpungan, Kec. Sekolilo, S...",

                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFAEAEAE),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xFF00AA25), shape = RoundedCornerShape(size = 25.dp))
                        .width(55.dp)
                        .height(22.dp)
                        .background(color = Color(0x0D00AA25), shape = RoundedCornerShape(size = 25.dp))
                        .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 4.dp)
                ){
                    Text(
                        text = "Open",

                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF00AA25),
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Close 10PM",

                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFAAAAAA),
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
fun DestinationFieldContainer(

){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0x40D8D8D8),
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
                    text = "Your Location",
                    
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 17.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF404040),
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
                    color = Color(0xFFD8D8D8),
                    modifier = Modifier
                        .background(color = Color(0xFFCACACA), shape = RoundedCornerShape(size = 4.dp))
                        .weight(1f)
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
                    text = "Universitas 17 Agustus 1945 SU...",

                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 17.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF404040),
                    ),

                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
//@Preview
fun VehicleUsedButton(
    isFocus: Boolean = false,
    imageResource: Int = R.drawable.ic_motorcycle,
    text: String = "15 min"
){
    val backgroundColor = if (isFocus) Color(0xFF52B040) else Color(0xFFFAFAFA)
    val iconColor = if (isFocus) Color(0xFFFAFAFA) else Color(0xFF52B040)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(width = 1.dp, color = Color(0xFF52B040), shape = RoundedCornerShape(size = 8.dp))
            .size(74.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(size = 8.dp))
            .padding(16.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Vehicle Icon",
                modifier = Modifier.size(24.dp),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(iconColor)
            )

            Text(
                text = "15 min",

                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(500),
                    color = iconColor,
                )
            )
        }
    }
}

@Composable
//@Preview
fun DestinationCard(){
    Box(
        modifier = Modifier
            .width(393.dp)
            .height(334.dp)
            .background(color = Color(0xFFFAFAFA), shape = RoundedCornerShape(size = 24.dp))
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
                    isFocus = true,
                    imageResource = R.drawable.ic_car,
                    text = "15 min"
                )
                VehicleUsedButton(
                    isFocus = false,
                    imageResource = R.drawable.ic_motorcycle,
                    text = "15 min"
                )
                VehicleUsedButton(
                    isFocus = false,
                    imageResource = R.drawable.ic_ev_cycle,
                    text = "15 min"
                )
                VehicleUsedButton(
                    isFocus = false,
                    imageResource = R.drawable.ic_footprints,
                    text = "15 min"
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
@Preview
fun TrackingStartedTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart) // Align the Row (arrow) to the start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "Back",
                modifier = Modifier.size(16.dp)
            )
        }

        Text(
            text = "Track Your Destination",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF1AA56A),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
//@Preview
fun TrackingStartedCard(){
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(96.dp)
    ){
        Box(
            modifier = Modifier
                .width(32.dp)
                .height(8.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
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
                .background(color = Color(0xFFFAFAFA), shape = RoundedCornerShape(size = 24.dp))
                .padding(24.dp)
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
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "15 mnt",

                        // label/l16/Semi Bold
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF1AA56A)
                        )
                    )

                    Text(
                        text = "500 m • 50 CO²g",


                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000)
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

