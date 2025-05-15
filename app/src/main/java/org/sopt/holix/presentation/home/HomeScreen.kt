package org.sopt.holix.presentation.home

import android.R.attr.maxLines
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PageSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.sopt.holix.core.designsystem.theme.Black
import org.sopt.holix.core.designsystem.theme.Blue
import org.sopt.holix.core.designsystem.theme.Gray01
import org.sopt.holix.core.designsystem.theme.Gray03
import org.sopt.holix.core.designsystem.theme.Gray04
import org.sopt.holix.core.designsystem.theme.Gray05
import org.sopt.holix.core.designsystem.theme.Gray06
import org.sopt.holix.core.designsystem.theme.Gray07
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.designsystem.theme.HolixTypography
import org.sopt.holix.core.designsystem.theme.White
import org.sopt.holix.presentation.home.dummy.dummyStudyList1
import org.sopt.holix.presentation.home.dummy.dummyStudyList2
import org.sopt.holix.presentation.home.model.Category
import org.sopt.holix.presentation.home.model.StudyUiModel
import org.sopt.holix.presentation.home.model.TagUiModel


class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HolixAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreenContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableStateOf(0) }
    var search by remember { mutableStateOf("") }
    val sections = listOf(
         dummyStudyList1,
         dummyStudyList2
    )

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
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            CategoryChips()
            Spacer(modifier = Modifier.height(23.dp))
        }

        items(sections) { studyList ->
            CourseSection(
                title = studyList.first().category,
                studies = studyList
            )
            Spacer(modifier = Modifier.height(60.dp))
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
            .fillMaxWidth(),
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
            placeholder = {
                Text(
                    text = "무엇을 배우고 싶으신가요?",
                    color = Gray03,
                    style = HolixTheme.typography.body3R15
                    )
                          },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_black),
                    contentDescription = "Search",
                    tint = Gray03
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 1.dp)
                .height(50.dp)
                .padding(end = 16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
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
                        style = if (selectedTab == index) HolixTheme.typography.body1Sb15 else HolixTheme.typography.body3R15,
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
            .fillMaxSize()

    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            pageSpacing = 0.dp,
            pageSize = PageSize.Fill
        ) { page ->
            Image(
                painter = painterResource(id = thumbnails[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 15.dp, end = 14.dp)
                .height(27.dp)
                .width(41.dp)
                .background(Gray07, RoundedCornerShape(20.dp))
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = "${pagerState.currentPage + 1} / ${thumbnails.size}",
                color = White,
                style = HolixTheme.typography.body7R13,
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

@Composable
fun CategoryChips() {
    val categories = listOf(
        Category(R.drawable.ic_career, "커리어"),
        Category(R.drawable.ic_tech, "재테크"),
        Category(R.drawable.ic_heart, "자기계발"),
        Category(R.drawable.ic_studying, "학업")
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
    ) {
        categories.forEach { category ->
            CategoryChipItem(category)
        }
    }
}

@Composable
fun CategoryChipItem(category: Category) {
    Column(
        modifier = Modifier
            .size(width = 78.dp, height = 72.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Gray01),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = category.iconRes),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 11.dp, bottom = 15.dp)
                .height(18.dp)
                .width(17.dp)

        )
        Text(
            text = category.label,
            style = HolixTheme.typography.body7R13,
            color = Black
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Composable
fun CourseSection(
    title: String,
    studies: List<StudyUiModel>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = HolixTheme.typography.title1B17,
                color = Black
            )
            Text(
                modifier = Modifier
                    .padding(end = 6.dp),
                text = "더보기",
                style = HolixTheme.typography.body2M15,
                color = Gray04
            )
        }
        Spacer(modifier = Modifier.height(0.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = spacedBy(15.dp)
        ) {
            items(studies) { study ->
                CourseCardItem(study)
            }
        }
    }
}

@Composable
fun CourseCardItem(
    study: StudyUiModel
) {
    Column(
        modifier = Modifier
            .width(256.dp)
            .height(228.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = study.imageUrl),
            contentDescription = study.studyTitle,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp))

        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
            study.tags.forEach { tag ->
                TagChip(tag)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = study.studyTitle,
            style = HolixTheme.typography.title3B15,
            color = Black,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = study.studyLeader,
            style = HolixTheme.typography.body6M13,
            color = Gray03,
            maxLines = 1
        )
        Text(
            text = study.price,
            style = HolixTheme.typography.body4B13,
            color = Black
        )
    }
}

@Composable
fun TagChip(tag: TagUiModel) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = when (tag.color) {
                    "blue" -> Blue
                    "gray" -> Gray06
                    else -> Gray06
                },
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 7.dp, vertical = 3.dp)
    ){
        Text(
            text = tag.name,
            style = HolixTheme.typography.label2M11,
            color = when (tag.color) {
                "blue" -> Blue
                "gray" -> Gray06
                else -> Gray06
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HolixAndroidTheme {
        HomeScreenContent(modifier = Modifier)
    }
}