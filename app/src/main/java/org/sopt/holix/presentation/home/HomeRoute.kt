package org.sopt.holix.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.collections.immutable.PersistentList
import org.sopt.holix.core.designsystem.theme.Gray01
import org.sopt.holix.core.designsystem.theme.White
import org.sopt.holix.core.util.UiState
import org.sopt.holix.domain.model.home.StudyEntity
import org.sopt.holix.presentation.home.component.BannerCarousel
import org.sopt.holix.presentation.home.component.CategoryChips
import org.sopt.holix.presentation.home.component.CourseSection
import org.sopt.holix.presentation.home.component.SearchTopBar
import org.sopt.holix.presentation.home.component.TabRowSection
import androidx.compose.runtime.getValue

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    snackBarHostState: SnackbarHostState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.getHomeStudyData()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { effect ->
                when (effect) {
                    is HomeSideEffect.ShowSnackBar -> snackBarHostState.showSnackbar(effect.message)
                    else -> Unit
                }
            }
    }

    HomeScreen(
        paddingValues = paddingValues,
        search = state.search,
        selectedTab = state.selectedTab,
        uiState = state.uiState,
        onSearchChanged = viewModel::onSearchChanged,
        onTabSelected = viewModel::onTabSelected
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    search: String,
    selectedTab: Int,
    uiState: UiState<PersistentList<List<StudyEntity>>>,
    onSearchChanged: (String) -> Unit,
    onTabSelected: (Int) -> Unit
) {
        LazyColumn(
            modifier = Modifier
                .background(color = White)
                .padding(paddingValues)
        ) {
            item {
                SearchTopBar(
                    search = search,
                    onSearchChange = onSearchChanged,
                    onMenuClick = { }
                )
                Spacer(modifier = Modifier.height(3.dp))
            }

            stickyHeader {
                TabRowSection(
                    selectedTab = selectedTab,
                    onTabSelected = onTabSelected
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

            when (uiState) {
                is UiState.Loading -> {
                    item{ LoadingIndicator() }
                }
                is UiState.Success -> {
                    itemsIndexed(uiState.data) { index, studyList ->
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
                        ErrorMessage(text = uiState.message)
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

