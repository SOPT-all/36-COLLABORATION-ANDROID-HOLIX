package org.sopt.holix.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.holix.core.designsystem.theme.Gray01
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.White
import org.sopt.holix.core.util.UiState
import org.sopt.holix.presentation.home.component.BannerCarousel
import org.sopt.holix.presentation.home.component.CategoryChips
import org.sopt.holix.presentation.home.component.CourseSection
import org.sopt.holix.presentation.home.component.TabRowSection
import org.sopt.holix.presentation.home.component.SearchTopBar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeSideEffect.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(effect.message)
                }

                is HomeSideEffect.NavigateNext -> {
                    // TODO: navController.navigate("clubDetailHome")
                }

                else -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .background(color = White)
                .padding(paddingValues)
        ) {
            item {
                SearchTopBar(
                    search = uiState.search,
                    onSearchChange = { viewModel.onSearchChanged(it) },
                    onMenuClick = { }
                )
                Spacer(modifier = Modifier.height(3.dp))
            }

            stickyHeader {
                TabRowSection(
                    modifier = Modifier,
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

            when (val result = uiState.uiState) {
                is UiState.Loading -> {
                    item{ LoadingIndicator() }
                }
                is UiState.Success -> {
                    itemsIndexed(result.data) { index, studyList ->
                        val categoryTitle = studyList.firstOrNull()?.category.orEmpty()
                        CourseSection(
                            title = categoryTitle,
                            studies = studyList
                        )
                        Spacer(modifier = Modifier.height(60.dp))
                    }
                }
                is UiState.Failure -> {
                    item {
                        ErrorMessage(text = result.message)
                    }
                }
                is UiState.Empty -> {
                    item {
                        Text(
                            text = "표시할 스터디가 없습니다.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                            color = Gray01
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Red)
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HolixAndroidTheme {
        HomeScreen()
    }
}