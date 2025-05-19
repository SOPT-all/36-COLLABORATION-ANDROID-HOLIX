package org.sopt.holix.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.presentation.home.component.BannerCarousel
import org.sopt.holix.presentation.home.component.CategoryChips
import org.sopt.holix.presentation.home.component.CourseSection
import org.sopt.holix.presentation.home.component.TabRowSection
import org.sopt.holix.presentation.home.component.TopBar
import org.sopt.holix.presentation.home.dummyData.dummyStudyList1
import org.sopt.holix.presentation.home.dummyData.dummyStudyList2



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
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


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HolixAndroidTheme {
        HomeScreen()
    }
}