package com.gi2.footwork.ui.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gi2.footwork.R


@Composable
fun HomeAppBar(
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color = Color(0xFFFAFAFA))
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .alpha(0.75f)
                    .width(40.dp)
                    .height(40.dp)
                    .background(color = Color(0xFFFEFEFE), shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Hi Arnold",

                    // label/l16/Semi Bold
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF404040),
                    )
                )

                Text(
                    text = "Aug 17, 2024",
                    style = TextStyle(
                        fontSize = 12.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFB8B8B8),
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp)) // Apply 8.dp corner radius
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_avatar),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize() // Fill the size of the Box
            )
        }

    }
}

@Composable
fun HomeScreenContent(

) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
        ) {
            HomeAppBar()
            Spacer(modifier = Modifier.height(16.dp))

            CardContainer()

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Contribution",

                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF404040)
                ),

                modifier = Modifier
                    .align(Alignment.Start)
            )

            ContributionCard()

            Text(
                text = "Challenges",

                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF404040),
                ),

                modifier = Modifier
                    .align(Alignment.Start)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                repeat(3) {
                    ChallengeCard()
                }
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun StepsCard() {
    Box(
        modifier = Modifier
            .width(165.dp)
            .height(168.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF758E),
                        Color(0xFFB22F47),
                    )
                ),
            )
            .drawBehind {
                drawCircle(
                    color = Color(0x1AFFFFFF),
                    radius = 75.dp.toPx(),
                    center = Offset(70f, 20f)
                )

                drawCircle(
                    color = Color(0x1AFFFFFF),
                    radius = 120.dp.toPx(),
                    center = Offset(370f, 150f)
                )

                drawCircle(
                    color = Color(0x1AFFFFFF),
                    radius = 99.dp.toPx(),
                    center = Offset(370f, 210f)
                )
            }
            .padding(12.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_footprints),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp)
                )

                Text(
                    text = "Steps",

                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFAFAFA),
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "100 CO²g",

                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFAFAFA),
                )
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            CircularProgressIndicator(
                progress = { 0.5f },
                color = Color(0xFFFFB20D),
                gapSize = 0.dp,
                strokeWidth = 5.dp,
                strokeCap = StrokeCap.Butt,
                trackColor = Color(0x61FFFFFF),
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
            Text(
                text = "100m",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFAFAFA),
                ),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun CarCard() {
    Box(
        modifier = Modifier
            .width(165.dp)
            .height(168.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF5ECCFF),
                        Color(0xFF2F80B2),
                    )
                ),
            )
            .drawBehind {
                rotate(-5f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(125.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset((-68).dp.toPx(), 78.dp.toPx()),
                    )
                }

                rotate(-5f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(125.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset((-33).dp.toPx(), 120.dp.toPx()),
                    )
                }

                rotate(-45f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(125.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset(120.dp.toPx(), (-30).dp.toPx()),
                    )
                }

            }
            .padding(12.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_car),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp)
                )

                Text(
                    text = "Car",

                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFAFAFA),
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "1 Km",

                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFAFAFA),
                )
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            CircularProgressIndicator(
                progress = { 0.5f },
                color = Color(0xFFFFB20D),
                gapSize = 0.dp,
                strokeWidth = 5.dp,
                strokeCap = StrokeCap.Butt,
                trackColor = Color(0x61FFFFFF),
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "1Km",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFAFAFA),
                    )
                )
                Text(
                    text = "/5km",
                    style = TextStyle(
                        fontSize = 8.sp,
                        lineHeight = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFAFAFA),
                    )
                )
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun MotorCycleCard() {
    Box(
        modifier = Modifier
            .width(165.dp)
            .height(168.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF5ED1),
                        Color(0xFFB22F84),
                    )
                ),
            )
            .drawBehind {
                rotate(35f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(200.dp.toPx(), 200.dp.toPx()),
                        topLeft = Offset((0).dp.toPx(), 115.dp.toPx()),
                    )
                }

                rotate(35f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(200.dp.toPx(), 200.dp.toPx()),
                        topLeft = Offset((0).dp.toPx(), 135.dp.toPx()),
                    )
                }

                rotate(35f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(200.dp.toPx(), 200.dp.toPx()),
                        topLeft = Offset((0).dp.toPx(), 155.dp.toPx()),
                    )
                }

                rotate(35f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(150.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset((-25).dp.toPx(), (-55).dp.toPx()),
                    )
                }

                rotate(35f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(140.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset((-35).dp.toPx(), (-75).dp.toPx()),
                    )
                }

            }
            .padding(12.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_motorcycle),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp)
                )

                Text(
                    text = "Motorcycle",

                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFAFAFA),
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "500 m",

                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFAFAFA),
                )
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            CircularProgressIndicator(
                progress = { 0.5f },
                color = Color(0xFFFFB20D),
                gapSize = 0.dp,
                strokeWidth = 5.dp,
                strokeCap = StrokeCap.Butt,
                trackColor = Color(0x61FFFFFF),
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "500m",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFAFAFA),
                    )
                )
                Text(
                    text = "/5km",
                    style = TextStyle(
                        fontSize = 8.sp,
                        lineHeight = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFAFAFA),
                    )
                )
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun EvCycleCard() {
    Box(
        modifier = Modifier
            .width(165.dp)
            .height(168.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB675FF),
                        Color(0xFF752FB2),
                    )
                ),
            )
            .drawBehind {
                rotate(35f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(163.dp.toPx(), 163.dp.toPx()),
                        topLeft = Offset((50).dp.toPx(), (50).dp.toPx()),
                    )
                }

                rotate(35f){
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(188.dp.toPx(), 188.dp.toPx()),
                        topLeft = Offset((25).dp.toPx(), (-80).dp.toPx()),
                    )
                }

                drawCircle(
                    color = Color(0x1AFFFFFF),
                    radius = 60.dp.toPx(),
                    center = Offset(20.dp.toPx(), 230f)
                )

            }
            .padding(12.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_ev_cycle),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp)
                )

                Text(
                    text = "EV Cycle",

                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFAFAFA),
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "500 m",

                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFAFAFA),
                )
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            CircularProgressIndicator(
                progress = { 0.5f },
                color = Color(0xFFFFB20D),
                gapSize = 0.dp,
                strokeWidth = 5.dp,
                strokeCap = StrokeCap.Butt,
                trackColor = Color(0x61FFFFFF),
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "500m",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFAFAFA),
                    )
                )
                Text(
                    text = "/5km",
                    style = TextStyle(
                        fontSize = 8.sp,
                        lineHeight = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFAFAFA),
                    )
                )
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun CardContainer() {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier
            .height(352.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(4) { index ->
            when (index) {
                0 -> StepsCard()
                1 -> MotorCycleCard()
                2 -> CarCard()
                3 -> EvCycleCard()
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun ContributionCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(376.dp)
            .clip(RoundedCornerShape(16.dp))
    ){
        Image(
            painter = painterResource(id = R.drawable.bg_contribution),
            contentDescription = "image description",
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxWidth()
                .height(376.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFFFFFF).copy(alpha = 0.75f),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ){
                    CircularProgressIndicator(
                        progress = { 0.25f },
                        color = Color(0xFF52B040),
                        gapSize = 0.dp,
                        strokeWidth = 5.dp,
                        strokeCap = StrokeCap.Round,
                        trackColor = Color(0xFF99DDA8),
                        modifier = Modifier
                            .size(70.dp)
                            .align(Alignment.Center)
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "60 kg",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
//                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF101010),
                                letterSpacing = 0.01.sp,
                            )
                        )
                        Text(
                            text = "CO2",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
//                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF00881E),
                                letterSpacing = 0.06.sp,
                            )
                        )
                    }
                }

                HorizontalDivider(
                    modifier = Modifier
                        .height(80.dp)
                        .width(1.dp)
                        .background(color = Color(0xFFE0E0E0))
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_scooter),
                        contentDescription = "image description",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )

                    Text(
                        text = "Motorcycle",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF101010),
                            letterSpacing = 0.06.sp,
                        )
                    )

                    LinearProgressIndicator(
                        progress = { 0.5f },
                        color = Color(0xFF52B040),
                        trackColor = Color(0xFF99DDA8),
                        gapSize = 0.dp,
                        strokeCap = StrokeCap.Round,
                        drawStopIndicator = {},
                        modifier = Modifier
                            .width(70.dp)
                    )

                    Text(
                        text = "40 kg CO2",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF101010),
                            letterSpacing = 0.06.sp,
                        )
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .height(80.dp)
                        .width(1.dp)
                        .background(color = Color(0xFFE0E0E0))
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_bus),
                        contentDescription = "image description",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )

                    Text(
                        text = "Car",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF101010),
                            letterSpacing = 0.06.sp,
                        )
                    )

                    LinearProgressIndicator(
                        progress = { 0.5f },
                        color = Color(0xFF52B040),
                        trackColor = Color(0xFF99DDA8),
                        gapSize = 0.dp,
                        strokeCap = StrokeCap.Round,
                        drawStopIndicator = {},
                        modifier = Modifier
                            .width(70.dp)
                    )

                    Text(
                        text = "20 kg CO2",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF101010),
                            letterSpacing = 0.06.sp,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ChallengeProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float = 0.3f,
    color : Color = Color(0xFF52B040),
    trackColor: Color = Color(0xFFF4F3F3),
    clipShape: Shape = RoundedCornerShape(6.dp)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(clipShape)
            .background(trackColor)
            .height(24.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(clipShape)
                .background(color)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}

@Composable
//@Preview(showBackground = true)
fun ChallengeCard() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .shadow(elevation = 8.dp, spotColor = Color(0x14000000), ambientColor = Color(0x14000000))
            .width(345.dp)
            .height(101.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = "Share positive moments",

            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 17.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(500),
                color = Color(0xFF404040),
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xFFCDCDCD), shape = RoundedCornerShape(size = 10.dp))
                .padding(4.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(24.dp)
            ){
                ChallengeProgressIndicator(
                    progress = 0.7f,
                    modifier = Modifier
                        .height(24.dp)
                )

                Text(
                    text = "3/7",

                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFAFAFA),
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))


            Text(
                text = "3",

                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFEAB01C),
                )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_cup_star),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, apiLevel = 30)
fun HomeScreenPreview(

){
    HomeScreenContent()
}