package org.sopt.holix.presentation.club_detail_home

import androidx.annotation.DrawableRes
import coil.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.input.pointer.motionEventSpy

enum class ClubMenuItem(val label: String, @DrawableRes val iconRes: Int) {
    MEETING("모임", R.drawable.ic_club_category_4),
    MENTORING("멘토링", R.drawable.ic_club_category_1),
    CLASS("클래스", R.drawable.ic_club_category_2),
    QUIZ("퀴즈", R.drawable.ic_club_category_3);

    companion object {
        val items get() = entries
    }
}

@Composable
fun ClubDetailHomeRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateNext: () -> Unit,
    snackBarHostState: SnackbarHostState,
    viewModel: ClubDetailHomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.getClubDetailHomeUsers()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                is ClubDetailHomeSideEffect.ShowSnackBar -> snackBarHostState.showSnackbar(
                    sideEffect.message
                )

                ClubDetailHomeSideEffect.NavigateNext -> navigateNext()
                ClubDetailHomeSideEffect.NavigateUp -> navigateUp()
                else -> Unit
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
            color = Color.Transparent, darkIcons = true
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = HolixTheme.colors.white)
                    .then(modifier)
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) {
                    // 배너이미지와 상단바를 겹치게 Box로 감싸기
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(360f / 324f)
                    ) {
                        AsyncImage(
                            model = R.drawable.img_club_main_and,
                            contentDescription = "배너 이미지",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                                .clip(RoundedCornerShape(topStart = 13.dp, topEnd = 13.dp))
                                .background(HolixTheme.colors.white)
                                .align(Alignment.BottomCenter)
                        )
                        ClubDetailTopAppBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .align(Alignment.TopStart),
                            navigateUp = navigateUp,
                            onSearchClick = {}
                        )
                    }

                    val clubMenuItems = ClubMenuItem.items
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-40).dp)
                            .clip(RoundedCornerShape(topStart = 13.dp, topEnd = 13.dp))
                            .background(HolixTheme.colors.white)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(HolixTheme.colors.white)
                                .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 12.dp)
                        ) {
                            // Title
                            Text(
                                text = "💰 디자이너로서 성공하고 싶은 사람들이 모인 방",
                                style = HolixTheme.typography.title1B17,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            // Member info
                            Row(
                                modifier = Modifier.padding(top = 12.dp, bottom = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_chatting_menu_4),
                                    contentDescription = "",
                                    tint = HolixTheme.colors.gray04
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "멤버 130/500",
                                    style = HolixTheme.typography.body6M13,
                                    color = HolixTheme.colors.gray05
                                )
                            }
                            // 공지
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(46.dp)
                                    .background(
                                        HolixTheme.colors.lightBlue,
                                        RoundedCornerShape(7.dp)
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(all = 13.dp)
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_speaker),
                                        contentDescription = "확성기 아이콘",
                                        tint = Color.Unspecified
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "입장 후 간단하게 자기소개를 포함한 인사를 부탁드려요!",
                                        style = HolixTheme.typography.body6M13,
                                        color = HolixTheme.colors.gray07
                                    )
                                }
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right_gray),
                                    contentDescription = "오른쪽 화살표",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.padding(end = 11.dp)
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .height(15.dp)
                                    .fillMaxWidth()
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = HolixTheme.colors.gray01,
                                thickness = 5.dp
                            )
                            // 메뉴바
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 12.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ClubMenuItem.items.forEachIndexed { index, item ->
                                    Column(
                                        modifier = Modifier
                                            .width(74.dp)
                                            .height(71.dp)
                                            .clickable { println("Clicked on ${item.label}") },
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Top
                                    ) {
                                        Spacer(modifier = Modifier.height(13.dp))
                                        Icon(
                                            painter = painterResource(id = item.iconRes),
                                            contentDescription = item.label,
                                            tint = HolixTheme.colors.gray06
                                        )
                                        Spacer(modifier = Modifier.height(9.dp))
                                        Text(
                                            text = item.label,
                                            style = HolixTheme.typography.label2M11,
                                            color = HolixTheme.colors.gray07
                                        )
                                    }
                                    if (index != ClubMenuItem.items.lastIndex) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = 12.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Divider(
                                                color = HolixTheme.colors.gray01,
                                                modifier = Modifier
                                                    .width(1.dp)
                                                    .height(24.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // 말풍선 + 채팅 버튼을 Box의 하단에 고정
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = HolixTheme.colors.white)
                            .padding(paddingValues)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.speech_bubble_and),
                                contentDescription = "채팅 안내 말풍선",
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.Start)
                            )
                            Spacer(modifier = Modifier
                                .height(9.dp)
                            )
                            Text(
                                text = "채팅 입장하기",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .background(color = HolixTheme.colors.mainBlue)
                                    .clickable { navigateNext() }
                                    .padding(vertical = 13.dp)
                                    .defaultMinSize(minHeight = 52.dp),
                                style = HolixTheme.typography.title2Sb15,
                                color = HolixTheme.colors.white,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
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
                        firstName = "지우",
                        lastName = "송",
                        profile = "https://example.com/profile.jpg"
                    )
                )
            )
        )
    }
}

@Composable
fun ClubDetailTopAppBar(
    navigateUp: () -> Unit, onSearchClick: () -> Unit, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.6f), Color.Transparent
                    )
                )
            )
            .padding(
                top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + 4.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left_white),
                contentDescription = "뒤로가기",
                tint = Color.White,
                modifier = Modifier.noRippleClickable { navigateUp() })
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_white),
                contentDescription = "검색",
                tint = Color.White,
                modifier = Modifier.noRippleClickable { onSearchClick() })
        }
    }
}


