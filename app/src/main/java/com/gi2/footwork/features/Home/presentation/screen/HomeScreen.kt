package com.gi2.footwork.features.Home.presentation.screen

import BottomNavigationBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.zIndex
import com.gi2.footwork.R
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.contributionProgressIndicatorTrack
import com.gi2.footwork.ui.theme.homeSectionTitle
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed
import com.gi2.footwork.ui.theme.statsProgressIndicator
import com.gi2.footwork.ui.theme.userGreetingSubText


@Composable
//@Preview
fun HomeAppBar(
    userName: String = "Arnold",
    date: String = "Aug 17, 2024",
    userPhoto: Int = R.drawable.user_avatar
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(8.dp),
                        ambientColor = Color(0x1A000000)
                    )
                    .background(color = Color(0xFFFEFEFE), shape = RoundedCornerShape(size = 8.dp))
                    .alpha(0.75f)
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
                    text = "Hi $userName",

                    style = AppTypography.titleMedium.copy(
                        fontWeight = FontWeight(600),
                        color = homeSectionTitle,
                    )
                )

                Text(
                    text = date,

                    style = AppTypography.bodySmall.copy(
                        fontWeight = FontWeight(400), color = userGreetingSubText
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(id = userPhoto),
                contentDescription = "user avatar",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}

@Composable
fun HomeScreenContent(

) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(112.dp)
                    .background(
                        color = onPrimaryFixed
                    )
                    .padding(
                        top = 0.dp, start = 8.dp, bottom = 16.dp, end = 24.dp
                    )

            ) {
                HomeAppBar()
            }
        },
        bottomBar = {
            Row(modifier= Modifier.fillMaxWidth().height(120.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                BottomNavigationBar()
            }
            },
        containerColor = onPrimaryFixed, modifier = Modifier.fillMaxSize()
    ) { innerPadding ->



        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
        ) {
            CardContainer()

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Contribution",

                style = AppTypography.titleMedium.copy(
                    fontWeight = FontWeight(600), color = homeSectionTitle
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            ContributionCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            Text(
                text = "Challenges",

                style = AppTypography.titleMedium.copy(
                    fontWeight = FontWeight(600), color = homeSectionTitle
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ChallengeCard(
                    name = "Share positive moments", done = 7, total = 7
                )
                repeat(3) {
                    ChallengeCard()
                }
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun CardContainer() {
    @Composable
    fun StepsCard(
        emission: Int = 100,
        distance: String = "100m",
    ) {
        val progress = 0.5f

        Box(modifier = Modifier
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
                    color = Color(0x1AFFFFFF), radius = 75.dp.toPx(), center = Offset(70f, 20f)
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
            .padding(12.dp)) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_footprints),
                        contentDescription = "Steps icon",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp)
                    )

                    Text(
                        text = "Steps",

                        style = AppTypography.labelLarge.copy(
                            color = onPrimaryFixed
                        )
                    )
                }

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "$emission CO²g",

                    style = AppTypography.labelMedium.copy(
                        fontWeight = FontWeight(400), color = onPrimaryFixed
                    )
                )
            }

            Box(
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                CircularProgressIndicator(
                    progress = { progress },
                    color = statsProgressIndicator,
                    gapSize = 0.dp,
                    strokeWidth = 5.dp,
                    strokeCap = StrokeCap.Butt,
                    trackColor = Color(0x61FFFFFF),
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )

                Text(
                    text = distance,

                    style = AppTypography.labelMedium.copy(
                        fontWeight = FontWeight(700), color = onPrimaryFixed
                    ),

                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

    @Composable
    fun CarCard(
        distance: Float = 1f,
        target: Float = 5f,
    ) {
        val progress = distance / target

        Box(modifier = Modifier
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
                rotate(-5f) {
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(125.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset((-68).dp.toPx(), 78.dp.toPx()),
                    )
                }

                rotate(-5f) {
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(125.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset((-33).dp.toPx(), 120.dp.toPx()),
                    )
                }

                rotate(-45f) {
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(125.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset(120.dp.toPx(), (-30).dp.toPx()),
                    )
                }

            }
            .padding(12.dp)) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_car),
                        contentDescription = "Car icon",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp)
                    )

                    Text(
                        text = "Car",

                        style = AppTypography.labelLarge.copy(
                            color = onPrimaryFixed
                        )
                    )
                }

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "${distance.toInt()} Km",

                    style = AppTypography.labelMedium.copy(
                        fontWeight = FontWeight(400), color = onPrimaryFixed
                    )
                )
            }

            Box(
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                CircularProgressIndicator(
                    progress = { progress },
                    color = statsProgressIndicator,
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
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "${distance.toInt()}Km",

                        style = AppTypography.labelMedium.copy(
                            fontWeight = FontWeight(700), color = onPrimaryFixed
                        )
                    )

                    Text(
                        text = "/${target.toInt()}km",

                        /* TODO: Add a style for this text */
                        style = TextStyle(
                            fontSize = 8.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight(400),
                            color = onPrimaryFixed,
                        )
                    )
                }
            }
        }
    }

    @Composable
    fun MotorCycleCard(
        distance: Float = 500f,
        target: Float = 5000f,
    ) {
        val progress = distance / target
        val targetKm = target / 1000

        Box(modifier = Modifier
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
                rotate(35f) {
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(200.dp.toPx(), 200.dp.toPx()),
                        topLeft = Offset((0).dp.toPx(), 115.dp.toPx()),
                    )
                }

                rotate(35f) {
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(200.dp.toPx(), 200.dp.toPx()),
                        topLeft = Offset((0).dp.toPx(), 135.dp.toPx()),
                    )
                }

                rotate(35f) {
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(200.dp.toPx(), 200.dp.toPx()),
                        topLeft = Offset((0).dp.toPx(), 155.dp.toPx()),
                    )
                }

                rotate(35f) {
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(150.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset((-25).dp.toPx(), (-55).dp.toPx()),
                    )
                }

                rotate(35f) {
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(140.dp.toPx(), 125.dp.toPx()),
                        topLeft = Offset((-35).dp.toPx(), (-75).dp.toPx()),
                    )
                }

            }
            .padding(12.dp)) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_motorcycle),
                        contentDescription = "Motorcycle icon",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp)
                    )

                    Text(
                        text = "Motorcycle",

                        style = AppTypography.labelLarge.copy(
                            color = onPrimaryFixed
                        )
                    )
                }

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "${distance.toInt()} m",

                    style = AppTypography.labelMedium.copy(
                        fontWeight = FontWeight(400), color = onPrimaryFixed
                    )
                )
            }

            Box(
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                CircularProgressIndicator(
                    progress = { progress },
                    color = statsProgressIndicator,
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
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "${distance.toInt()}m",

                        style = AppTypography.labelMedium.copy(
                            fontWeight = FontWeight(700), color = onPrimaryFixed
                        )
                    )

                    Text(
                        text = "/${targetKm.toInt()}km",

                        /* TODO: Add a style for this text */
                        style = TextStyle(
                            fontSize = 8.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight(400),
                            color = onPrimaryFixed,
                        )
                    )
                }
            }
        }
    }

    @Composable
    fun EvCycleCard(
        distance: Float = 700f,
        target: Float = 5000f,
    ) {
        val progress = distance / target
        val targetKm = target / 1000

        Box(modifier = Modifier
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
                rotate(35f) {
                    drawRoundRect(
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                        color = Color(0x1AFFFFFF),
                        size = Size(163.dp.toPx(), 163.dp.toPx()),
                        topLeft = Offset((50).dp.toPx(), (50).dp.toPx()),
                    )
                }

                rotate(35f) {
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
            .padding(12.dp)) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
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
                        text = "Ev Cycle",

                        style = AppTypography.labelLarge.copy(
                            color = onPrimaryFixed
                        )
                    )
                }

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "${distance.toInt()} m",

                    style = AppTypography.labelMedium.copy(
                        fontWeight = FontWeight(400), color = onPrimaryFixed
                    )
                )
            }

            Box(
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                CircularProgressIndicator(
                    progress = { progress },
                    color = statsProgressIndicator,
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
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "${distance.toInt()}m",

                        style = AppTypography.labelMedium.copy(
                            fontWeight = FontWeight(700), color = onPrimaryFixed
                        )
                    )

                    Text(
                        text = "/${targetKm.toInt()}km",

                        /* TODO: Add a style for this text */
                        style = TextStyle(
                            fontSize = 8.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight(400),
                            color = onPrimaryFixed,
                        )
                    )
                }
            }
        }
    }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.height(352.dp),
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
fun ContributionCard(
    modifier: Modifier = Modifier,
    emissionTotal: Float = 60f,
    maxEmission: Float = 240f,
    emissionFromMotorcycle: Float = 40f,
    maxMotorCycleEmission: Float = 80f,
    emissionFromCar: Float = 20f,
    maxCarEmission: Float = 160f,
) {
    val totalEmissionProgress = emissionTotal / maxEmission
    val motorcycleEmissionProgress = emissionFromMotorcycle / maxMotorCycleEmission
    val carEmissionProgress = emissionFromCar / maxCarEmission

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(376.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_contribution),
            contentDescription = "Contribution background",
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
                    color = Color.White.copy(alpha = 0.75f), shape = RoundedCornerShape(16.dp)
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
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    CircularProgressIndicator(
                        progress = { totalEmissionProgress },
                        color = primaryFixed,
                        gapSize = (-4).dp,
                        strokeWidth = 5.dp,
                        strokeCap = StrokeCap.Round,
                        trackColor = contributionProgressIndicatorTrack,
                        modifier = Modifier
                            .size(70.dp)
                            .align(Alignment.Center)
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            text = "${emissionTotal.toInt()} kg",

                            style = AppTypography.titleMedium.copy(
                                fontWeight = FontWeight(600), color = homeSectionTitle
                            )
                        )

                        Text(
                            text = "CO2",

                            style = AppTypography.bodySmall.copy(
                                fontWeight = FontWeight(600), color = Color(0xFF00881E)
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
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_scooter),
                        contentDescription = "Motorcycle Icon",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )

                    Text(
                        text = "Motorcycle",

                        style = AppTypography.labelSmall.copy(
                            fontWeight = FontWeight(500), color = Color(0xFF101010)
                        )
                    )

                    LinearProgressIndicator(
                        progress = { motorcycleEmissionProgress },
                        color = primaryFixed,
                        trackColor = contributionProgressIndicatorTrack,
                        gapSize = (-4).dp,
                        strokeCap = StrokeCap.Round,
                        drawStopIndicator = {},
                        modifier = Modifier.width(70.dp)
                    )

                    Text(
                        text = "${emissionFromMotorcycle.toInt()} kg CO2",

                        style = AppTypography.labelSmall.copy(
                            fontWeight = FontWeight(500), color = Color(0xFF101010)
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
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bus),
                        contentDescription = "Car icon",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )

                    Text(
                        text = "Car",

                        style = AppTypography.labelSmall.copy(
                            fontWeight = FontWeight(500), color = Color(0xFF101010)
                        )
                    )

                    LinearProgressIndicator(
                        progress = { carEmissionProgress },
                        color = primaryFixed,
                        trackColor = contributionProgressIndicatorTrack,
                        gapSize = (-4).dp,
                        strokeCap = StrokeCap.Round,
                        drawStopIndicator = {},
                        modifier = Modifier.width(70.dp)
                    )

                    Text(
                        text = "${emissionFromCar.toInt()} kg CO2",

                        style = AppTypography.labelSmall.copy(
                            fontWeight = FontWeight(500), color = Color(0xFF101010)
                        )
                    )
                }
            }
        }
    }
}


@Composable
//@Preview(showBackground = true)
fun ChallengeCard(
    name: String = "Share positive moments", done: Int = 5, total: Int = 7
) {
    val challengeProgress = done.toFloat() / total

    @Composable
    fun ChallengeProgressIndicator(
        modifier: Modifier = Modifier,
        progress: Float = 0.3f,
        color: Color = primaryFixed,
        trackColor: Color = Color(0xFFF4F3F3),
        clipShape: Shape = RoundedCornerShape(6.dp),
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
    fun ClaimButton(
        modifier: Modifier = Modifier, onClick: () -> Unit
    ) {
        Box(modifier = modifier.clickable { onClick() }) {
//            Red Dot
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
                    .zIndex(2f)
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(color = Color(0xFFFF4C31), shape = RoundedCornerShape(50.dp))
                        .align(Alignment.TopEnd)
                )
            }

            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 6.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = primaryFixed)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Claim",

                        style = AppTypography.labelLarge.copy(
                            fontWeight = FontWeight(600), color = onPrimaryFixed
                        )
                    )
                }
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .shadow(
                elevation = 8.dp, spotColor = Color(0x14000000), ambientColor = Color(0x14000000)
            )
            .width(345.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = name,

            style = AppTypography.titleMedium.copy(
                fontWeight = FontWeight(500), color = homeSectionTitle
            )
        )

        Box(
            contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxWidth()
        ) {
            val isCompleted = (done == total)

            if (isCompleted) {
                ClaimButton(modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth()
                    .padding(end = 64.dp)
                    .zIndex(1f)
                    .height(54.dp), onClick = {
                    /* TODO */
                })
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFFCDCDCD),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .padding(4.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .height(24.dp)
                ) {
                    ChallengeProgressIndicator(
                        progress = challengeProgress, modifier = Modifier.height(24.dp)
                    )

                    Text(
                        text = "$done/$total",

                        style = AppTypography.labelLarge.copy(
                            fontWeight = FontWeight(600), color = onPrimaryFixed
                        )
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))


                Text(
                    text = done.toString(),

                    style = AppTypography.titleLarge.copy(
                        fontWeight = FontWeight(600), color = Color(0xFFEAB01C)
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_cup_star),
                    contentDescription = "Trophy icon",
                    contentScale = ContentScale.None
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview(

) {
    HomeScreenContent()
}