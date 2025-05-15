package org.sopt.holix.presentation.club_detail_home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.util.UiState
import org.sopt.holix.core.util.noRippleClickable
import org.sopt.holix.domain.model.DummyUser
import org.sopt.holix.presentation.dummy.DummySideEffect
import org.sopt.holix.presentation.dummy.DummyViewModel

data class ClubMenuItem(
    val label: String,
    @DrawableRes val iconRes: Int,
    val onClick: () -> Unit
)

@Composable
fun ClubDetailHomeRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateNext: () -> Unit,
    snackBarHostState: SnackbarHostState,
    viewModel: DummyViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.getDummyList()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is DummySideEffect.ShowSnackBar -> snackBarHostState.showSnackbar(sideEffect.message)
                    DummySideEffect.NavigateNext -> navigateNext()
                    DummySideEffect.NavigateUp -> navigateUp()
                }
            }
    }

    ClubDetailHomeScreen(
        paddingValues = paddingValues,
        navigateUp = viewModel::navigateUp,
        navigateNext = viewModel::navigateNext,
        state = state.uiState
    )
}

@Composable
fun ClubDetailHomeScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateNext: () -> Unit,
    state: UiState<PersistentList<DummyUser>>,
    modifier: Modifier = Modifier
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }
    when (state) {
        is UiState.Loading -> {
            Text(
                text = stringResource(R.string.loading_string),
                style = HolixTheme.typography.body1Sb15,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            )
        }
        is UiState.Failure -> {
            Text(
                text = state.message,
                style = HolixTheme.typography.body1Sb15,
                color = HolixTheme.colors.alertRed,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            )
        }
        is UiState.Empty -> {
            Text(
                text = stringResource(R.string.empty_string),
                style = HolixTheme.typography.body1Sb15,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            )
        }
        is UiState.Success -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) {
                    // Top Bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 12.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left_white),
                            contentDescription = "뒤로가기",
                            tint = Color.Unspecified,
                            modifier = Modifier.noRippleClickable { navigateUp() }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_white),
                            contentDescription = "검색",
                            tint = Color.Unspecified,
                            modifier = Modifier.noRippleClickable { /* TODO */ }
                        )
                    }

                    // Banner Image
                    Image(
                        painter = painterResource(id = R.drawable.img_home_thumnail_4),
                        contentDescription = "클럽 배너",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .background(
                                color = HolixTheme.colors.white,
                                shape = RoundedCornerShape(topStart = 13.dp, topEnd = 13.dp)
                            )
                    ) {
                        Column {
                            Text(
                                text = "💰 디자이너로서 성공하고 싶은 사람들이 모인 방",
                                style = HolixTheme.typography.title1B17,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_chatting_menu_4),
                                    contentDescription = "멤버",
                                    tint = HolixTheme.colors.gray04
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "멤버 130/500",
                                    style = HolixTheme.typography.body6M13,
                                    color = HolixTheme.colors.gray05
                                )
                            }

                            // Notice
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .background(HolixTheme.colors.lightBlue, RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_speaker),
                                        contentDescription = "공지",
                                        tint = Color.Unspecified
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "입장 후 간단하게 자기소개를 포함한 인사를 부탁드려요!",
                                        style = HolixTheme.typography.label3R11,
                                        color = HolixTheme.colors.gray07
                                    )
                                }
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_arrow_right_gray),
                                    contentDescription = "공지",
                                    tint = Color.Unspecified
                                )
                            }

                            // Menu Buttons
                            val clubMenuItems = listOf(
                                ClubMenuItem("모임", R.drawable.ic_club_category_1) { },
                                ClubMenuItem("멘토링", R.drawable.ic_club_category_2) { },
                                ClubMenuItem("클래스", R.drawable.ic_club_category_3) { },
                                ClubMenuItem("퀴즈", R.drawable.ic_club_category_4) { }
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 4.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                clubMenuItems.forEachIndexed { index, item ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.noRippleClickable { item.onClick() }
                                    ) {
                                        Icon(
                                            painter = painterResource(id = item.iconRes),
                                            contentDescription = item.label,
                                            tint = Color.Unspecified
                                        )
                                        Text(
                                            text = item.label,
                                            style = HolixTheme.typography.label3R11,
                                            color = HolixTheme.colors.gray06
                                        )
                                    }
                                    if (index != clubMenuItems.lastIndex) {
                                        Box(
                                            modifier = Modifier
                                                .padding(horizontal = 12.dp)
                                                .fillMaxHeight(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Divider(
                                                color = HolixTheme.colors.gray01,
                                                modifier = Modifier
                                                    .width(1.dp)
                                                    .height(32.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.speech_bubble_and),
                        contentDescription = "채팅 안내 문구",
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = HolixTheme.colors.mainBlue)
                            .clickable { navigateNext() }
                            .padding(vertical = 14.dp)
                            .padding(horizontal = 16.dp)
                            .navigationBarsPadding()
                    ) {
                        Text(
                            text = "채팅 입장하기",
                            style = HolixTheme.typography.body1Sb15,
                            color = HolixTheme.colors.white,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ClubDetailHomeScreenPreview() {
    HolixAndroidTheme {
        ClubDetailHomeScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateNext = {},
            state = UiState.Success(
                persistentListOf(
                    DummyUser(
                        id = 1,
                        firstName = "혜음",
                        lastName = "송",
                        profile = "https://example.com/image.png"
                    )
                )
            )
        )
    }
}