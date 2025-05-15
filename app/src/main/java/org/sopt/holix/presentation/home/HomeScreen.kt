package org.sopt.holix.presentation.home

import android.R.attr.maxLines
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.material3.TextFieldDefaults
import org.sopt.holix.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.holix.core.designsystem.theme.Black
import org.sopt.holix.core.designsystem.theme.Blue
import org.sopt.holix.core.designsystem.theme.Gray03
import org.sopt.holix.core.designsystem.theme.Gray05
import org.sopt.holix.core.designsystem.theme.Gray07
import org.sopt.holix.core.designsystem.theme.White
import org.sopt.holix.presentation.home.ui.theme._36COLLABORATIONANDROIDHOLIXTheme

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _36COLLABORATIONANDROIDHOLIXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}

 @Composable
fun HomeScreen() {
     var selectedTab by remember { mutableStateOf(0) }
    LazyColumn{
        item{
            TopBar(
                search = search,
                onSearchChange = { search = it },
                onMenuClick = {  }
            )
            Spacer(modifier = Modifier.height(3.dp))
        }
        stickyHeader{
            TabRowSection(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
        item {
            BannerCarousel()
        }
        item {
            CategoryChips()

        }
        item {
            SectionTitle()

        }
        item {
            StudyItems()
        }
    }
}
@Composable
fun TopBar(
    search: String,
    onSearchChange: (String) -> Unit,
    onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(painter = painterResource(id = R.drawable.ic_hambeger),
                contentDescription = "Menu",
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        OutlinedTextField(
            value = search,
            onValueChange = onSearchChange,
            placeholder = { Text(text = "검색어를 입력해주세요") },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_black),
                    contentDescription = "Search",
                    tint = Color.Unspecified
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Gray03,
                unfocusedContainerColor = Gray03,
                focusedIndicatorColor = Gray03,
                unfocusedIndicatorColor = Gray03,
            ),
            singleLine = true
        )
    }
}

@Composable
fun TabRowSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val categories = listOf("추천", "강의", "스터디", "북클럽", "멘토링", "커뮤니티")
    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 0.dp,
        // backgroundColor = White,
        contentColor = Black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab])
                    .height(2.dp),
                color = Blue

            )
        },
        modifier = Modifier
            .height(46.dp)

    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                modifier = Modifier.width(64.dp),
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                selectedContentColor = Black,
                unselectedContentColor = Black,
                text = {
                    Text(
                        text = category,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            )
        }

    }
}

@Composable
fun BannerCarousel() {
    val thumbnails = listOf(
        R.drawable.img_home_thumnail_1,
        R.drawable.img_home_thumnail_2,
        R.drawable.img_home_thumnail_3,
        R.drawable.img_home_thumnail_4,
        R.drawable.img_home_thumnail_5,
        R.drawable.img_home_thumnail__and6,
    )
    val pagerState = rememberPagerState(
        pageCount = {thumbnails.size})

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)

    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = thumbnails[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .height(27.dp)
                .width(41.dp)
                .padding(end = 14.dp, top = 14.dp)
                .background(Gray07, RoundedCornerShape(20.dp))
        ) {
            Text(
                text = "${pagerState.currentPage + 1} / ${thumbnails.size}",
                color = White,
                fontSize = 13.sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 19.dp ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in thumbnails.indices) {
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .width(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (i == pagerState.currentPage) Gray05 else White
                        )
                )
                Spacer(modifier = Modifier.width(9.dp))
            }
        }
    }
}













//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    _36COLLABORATIONANDROIDHOLIXTheme {
//        HomeScreen("Android")
//    }
//}