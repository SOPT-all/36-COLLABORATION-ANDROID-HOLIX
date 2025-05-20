package org.sopt.holix.presentation.myclub

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.type.MyClubStatus
import org.sopt.holix.core.type.MyClubTab
import org.sopt.holix.core.util.UiState
import org.sopt.holix.domain.model.MyClubEntity
import org.sopt.holix.domain.model.RecommendClubEntity
import org.sopt.holix.presentation.myclub.component.MyClubCard
import org.sopt.holix.presentation.myclub.component.MyClubRecommendCard
import org.sopt.holix.presentation.myclub.component.MyClubTabRow

@Composable
fun MyClubRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateClubDetailHome: (Long) -> Unit,
    snackBarHostState: SnackbarHostState,
    viewModel: MyClubViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.getDummyMyClubList()
        viewModel.getRecommendClubList()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect{ sideEffect ->
                when(sideEffect) {
                    is MyClubSideEffect.ShowSnackBar -> snackBarHostState.showSnackbar(sideEffect.message)
                    MyClubSideEffect.NavigateUp -> navigateUp()
                    is MyClubSideEffect.NavigateClubDetailHome -> navigateClubDetailHome(sideEffect.clubId)
                }
            }
    }

    MyClubScreen(
        paddingValues = paddingValues,
        navigateUp = viewModel::navigateUp,
        navigateClubDetailHome = viewModel::navigateToClubDetailHome,
        state = state.uiState,
        recommendClub = state.recommendUiState,
        selectedTab = state.selectedTab,
        onTabSelected = viewModel::onTabSelected
    )
}

@Composable
fun MyClubScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateClubDetailHome: (Long) -> Unit,
    state: UiState<PersistentList<MyClubEntity>>,
    recommendClub: PersistentList<RecommendClubEntity>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        when (state) {
            is UiState.Loading -> {
                item {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.loading_string),
                        fontSize = 30.sp
                    )
                }
            }

            is UiState.Empty -> {
                item {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_string),
                        fontSize = 30.sp
                    )
                }
            }

            is UiState.Failure -> {
                item {
                    Text(
                        textAlign = TextAlign.Center,
                        text = state.message,
                        style = HolixTheme.typography.body1Sb15,
                        color = HolixTheme.colors.alertRed
                    )
                }
            }

            is UiState.Success -> {
                item{
                    MyClubTabRow(
                        tabType = MyClubTab.entries.toPersistentList(),
                        selectedTabIndex = selectedTab,
                        onTabSelected = {
                            onTabSelected(it)
                        }
                    )

                    Spacer(Modifier.height(16.dp))

                    state.data.chunked(2).forEachIndexed { index, rowClubs ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowClubs.forEachIndexed { index, club ->
                                MyClubCard(
                                    clubTitle = club.title,
                                    participants = club.participants,
                                    clubImage = club.url,
                                    isPinned = club.isPinned,
                                    onClick = {
                                        navigateClubDetailHome(club.clubId)
                                    },
                                    onPinButtonClick = {

                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(if(index == 0) PaddingValues(start = 16.dp) else PaddingValues(end = 16.dp))
                                )
                            }
                            if (rowClubs.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                        if (index != state.data.chunked(2).lastIndex) {
                            Spacer(Modifier.height(16.dp))
                        }
                    }

                    Spacer(Modifier.height(30.dp))
                }

                item{
                    Text(
                        text = stringResource(R.string.my_club_recommend),
                        style = HolixTheme.typography.title3B15,
                        color = HolixTheme.colors.black,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    Spacer(Modifier.height(8.dp))
                }

                itemsIndexed(items = recommendClub){ index, it ->
                    MyClubRecommendCard(
                        clubTitle = it.title,
                        clubMaster = it.clubMaster,
                        participants = it.participants,
                        myClubStatus = if(it.isRecruiting) MyClubStatus.RECRUITING else MyClubStatus.CLOSED,
                        clubImage = it.imageRes,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    if(index == recommendClub.lastIndex){
                        Spacer(Modifier.height(23.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyClubScreenPreview() {
    HolixAndroidTheme {
        MyClubScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateClubDetailHome = {},
            state = UiState.Success(
                data = persistentListOf(
                    MyClubEntity(
                        clubId = 1,
                        title = "디자이너로서 성공하고 싶은 사람들이 모인 방",
                        participants = "멤버 130명 / 500명",
                        url = "https://picsum.photos/360",
                        isPinned = true
                    ),
                    MyClubEntity(
                        clubId = 2,
                        title = "AI 도구 활용한 영상 만들기 질문 답변방",
                        participants = "멤버 130명 / 500명",
                        url = "https://picsum.photos/360",
                        isPinned = false
                    ),
                    MyClubEntity(
                        clubId = 3,
                        title = "손글씨를 사랑하는 사람들의 모임",
                        participants = "멤버 130명 / 500명",
                        url = "https://picsum.photos/360",
                        isPinned = true
                    ),
                    MyClubEntity(
                        clubId = 4,
                        title = "UX Writing 실무 파헤치기",
                        participants = "멤버 998명 / 1,000명",
                        url = "https://picsum.photos/360",
                        isPinned = false
                    )
                )
            ),
            recommendClub = persistentListOf(
                RecommendClubEntity(
                    title = "UI/UX 원칙&UX 분석 Case Study - 매일 업로드",
                    clubMaster = "전민수",
                    participants = "멤버 975명 / 1,000명",
                    isRecruiting = true,
                    imageRes = R.drawable.img_my_recommmend_1_and
                ),
                RecommendClubEntity(
                    title = "데일리 디자인 정보 공유 세션",
                    clubMaster = "이선주",
                    participants = "멤버 847명 / 1,500명",
                    isRecruiting = true,
                    imageRes = R.drawable.img_my_recommmend_2_and
                ),
                RecommendClubEntity(
                    title = "피그마 학습방 : 서비스 기획자와 함께해요!",
                    clubMaster = "기획자 김로리",
                    participants = "멤버 1,000명 / 1,000명",
                    isRecruiting = false,
                    imageRes = R.drawable.img_my_recommmend_3_and
                )
            ),
            selectedTab = 0,
            onTabSelected = {}
        )
    }
}