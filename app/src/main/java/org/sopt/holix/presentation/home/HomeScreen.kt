package org.sopt.holix.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.collections.immutable.persistentListOf
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.presentation.home.component.BannerCarousel
import org.sopt.holix.presentation.home.component.CategoryChips
import org.sopt.holix.presentation.home.component.CourseSection
import org.sopt.holix.presentation.home.component.TabRowSection
import org.sopt.holix.presentation.home.component.SearchTopBar
import org.sopt.holix.presentation.home.dummyData.dummyStudyList1
import org.sopt.holix.presentation.home.dummyData.dummyStudyList2


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState
    val sections = persistentListOf(
         dummyStudyList1,
         dummyStudyList2
    )

    LazyColumn{
        item{
            SearchTopBar(
                modifier = Modifier,
                search = uiState.search,
                onSearchChange = { viewModel.onSearchChanged(it) },
                onMenuClick = {  }
            )
            Spacer(modifier = Modifier.height(3.dp))
        }

        stickyHeader{
            TabRowSection(
                selectedTab = uiState.selectedTab,
                onTabSelected = { viewModel.onTabSelected(it) }
            )
        }

        item {
            BannerCarousel()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            CategoryChips()
            Spacer(modifier = Modifier.height(20.dp))
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