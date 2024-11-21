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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
@Preview
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