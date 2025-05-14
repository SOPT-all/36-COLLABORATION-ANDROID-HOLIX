package org.sopt.holix.presentation.chatting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.util.UiState
import org.sopt.holix.core.util.noRippleClickable
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity
import org.sopt.holix.domain.model.chatting.ChattingScreenType
import org.sopt.holix.domain.model.chatting.ChattingType
import org.sopt.holix.presentation.chatting.components.hamburger.ChattingClubExit
import org.sopt.holix.presentation.chatting.components.hamburger.ChattingHamburgerMenu
import org.sopt.holix.presentation.chatting.core.ChattingTopBar
import java.time.LocalDateTime

@Composable
fun ChattingHamburgerRoute(
    navigateUp: () -> Unit,
    navigateNext: () -> Unit,
    snackBarHostState: SnackbarHostState,
    viewModel: ChattingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    val hamburgerMenuList = listOf(
        Pair(R.drawable.ic_chatting_menu_5, R.string.chat_hamburger_menu_vote),
        Pair(R.drawable.ic_chatting_menu_1, R.string.chat_hamburger_menu_tag),
        Pair(R.drawable.ic_chatting_menu_3, R.string.chat_hamburger_menu_link),
        Pair(R.drawable.ic_chatting_menu_2, R.string.chat_hamburger_menu_image),
        Pair(R.drawable.ic_chatting_menu_4, R.string.chat_hamburger_menu_member)
    )

    LaunchedEffect(Unit) {
        viewModel.getChattingList(1)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is ChattingSideEffect.ShowSnackBar -> snackBarHostState.showSnackbar(sideEffect.message)
                    ChattingSideEffect.NavigateNext -> navigateNext()
                    ChattingSideEffect.NavigateUp -> navigateUp()
                }
            }
    }

    ChattingHamburgerScreen(
        state = state.uiState,
        hamburgerMenuList = hamburgerMenuList,
        navigateUp = navigateUp,
        modifier = Modifier
    )
}

@Composable
fun ChattingHamburgerScreen(
    state: UiState<PersistentList<ChattingListDataEntity>>,
    hamburgerMenuList : List<Pair<Int, Int>>, //이미지 icon, icon content description
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        containerColor = HolixTheme.colors.gray01,
        topBar = {
            ChattingTopBar(
                screenType = ChattingScreenType.Hamburger,
                navigateUp = {
                    navigateUp()
                }
            )
        },

        content = {
            LazyColumn (
                modifier = modifier
                    .padding(it)
                    .background(HolixTheme.colors.gray01),
            ){
                when (state) {
                    is UiState.Loading -> {
                        item {
                            Text(
                                modifier = modifier
                                    .noRippleClickable { /*navigateUp()*/ },
                                textAlign = TextAlign.Center,
                                text = stringResource(R.string.loading_string),
                                fontSize = 30.sp
                            )
                        }
                    }

                    is UiState.Empty -> {
                        item {
                            Text(
                                modifier = modifier
                                    .noRippleClickable { /*navigateUp()*/ },
                                textAlign = TextAlign.Center,
                                text = stringResource(R.string.empty_string),
                                fontSize = 30.sp
                            )
                        }
                    }

                    is UiState.Failure -> {
                        item {
                            Text(
                                modifier = modifier
                                    .noRippleClickable { /*navigateUp()*/ },
                                textAlign = TextAlign.Center,
                                text = state.message,
                                style = HolixTheme.typography.body1Sb15,
                                color = HolixTheme.colors.alertRed
                            )
                        }
                    }

                    is UiState.Success -> {
                        //Todo : 반복으로 할지 나눠서할지?
                        items(hamburgerMenuList.size) {hamburgerMenuIndex ->
                            ChattingHamburgerMenu(
                                imageVector = ImageVector.vectorResource(hamburgerMenuList[hamburgerMenuIndex].first),
                                contentDescription = stringResource(hamburgerMenuList[hamburgerMenuIndex].second),
                                menuName = stringResource(hamburgerMenuList[hamburgerMenuIndex].second),
                                memberList = state.data,
                                modifier = modifier
                            )

                            if (stringResource(hamburgerMenuList[hamburgerMenuIndex].second) == stringResource(R.string.chat_hamburger_menu_image) || stringResource(hamburgerMenuList[hamburgerMenuIndex].second) == stringResource(R.string.chat_hamburger_menu_member)) {
                                Spacer(modifier = modifier.height(18.dp))
                            } else {
                                Spacer(modifier = modifier.height(10.dp))
                            }
                        }

                        item {
                            ChattingClubExit()
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ChattingHamburgerScreenPreview() {
    val hamburgerMenuList = listOf(
        Pair(R.drawable.ic_chatting_menu_5, R.string.chat_hamburger_menu_vote),
        Pair(R.drawable.ic_chatting_menu_1, R.string.chat_hamburger_menu_tag),
        Pair(R.drawable.ic_chatting_menu_3, R.string.chat_hamburger_menu_link),
        Pair(R.drawable.ic_chatting_menu_2, R.string.chat_hamburger_menu_image),
        Pair(R.drawable.ic_chatting_menu_4, R.string.chat_hamburger_menu_member)
    )

    val dummyList = persistentListOf(
        ChattingListDataEntity(
            chattingId = 1,
            clubId = 1,
            userName = "송혜음",
            introduction = "저는 송혜음입니다.",
            imageUrl = "",
            contents = "이건 테스트 메시지입니다.",
            likes = 3,
            chattingType = ChattingType.USER,
            isMine = false,
            createdAt = LocalDateTime.now()
        ),

        ChattingListDataEntity(
            chattingId = 2,
            clubId = 1,
            userName = "조운재",
            introduction = "저는 조운재.",
            imageUrl = "",
            contents = "반갑습니다",
            likes = 3,
            chattingType = ChattingType.USER,
            isMine = true,
            createdAt = LocalDateTime.now()
        ),

        ChattingListDataEntity(
            chattingId = 3,
            clubId = 1,
            userName = "김솝트",
            introduction = "저는 김솝트.",
            imageUrl = "",
            contents = "김솝트님이 입장했습니다.",
            likes = 0,
            chattingType = ChattingType.SYSTEM,
            isMine = false,
            createdAt = LocalDateTime.now()
        ),

        ChattingListDataEntity(
            chattingId = 4,
            clubId = 1,
            userName = "전윤혁",
            introduction = "저는 전윤혁.",
            imageUrl = "",
            contents = "반갑습니다",
            likes = 3,
            chattingType = ChattingType.USER,
            isMine = true,
            createdAt = LocalDateTime.now()
        )
    )

    HolixAndroidTheme {
        ChattingHamburgerScreen(
            state = UiState.Success(dummyList),
            hamburgerMenuList = hamburgerMenuList,
            navigateUp = {}
        )
    }
}