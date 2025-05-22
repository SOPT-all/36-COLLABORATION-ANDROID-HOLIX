package org.sopt.holix.presentation.chatting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.util.UiState
import org.sopt.holix.core.util.noRippleClickable
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity
import org.sopt.holix.domain.model.chatting.ChattingScreenType
import org.sopt.holix.domain.model.chatting.ChattingType
import org.sopt.holix.presentation.chatting.components.detail.ChattingItem
import org.sopt.holix.presentation.chatting.components.detail.ChattingSystemMessage
import org.sopt.holix.presentation.chatting.components.detail.ChattingTextField
import org.sopt.holix.presentation.chatting.core.ChattingTopBar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ChattingRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateNext: () -> Unit,
    snackBarHostState: SnackbarHostState,
    viewModel: ChattingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        //Todo : 나중에 클럽아이디 받아오는 데이터로 변경
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

    //Todo : 나중에 이동 추가
    ChattingScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        state = state.uiState
    )
}

@Composable
fun ChattingScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    state: UiState<PersistentList<ChattingListDataEntity>>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .navigationBarsPadding()
            .padding(paddingValues),
        containerColor = HolixTheme.colors.gray01,
        topBar = {
            ChattingTopBar(
                screenType = ChattingScreenType.Detail,
                navigateUp = navigateUp
            )
        },

        bottomBar = {
            ChattingTextField(
                chat = "",
                onTextChanged = {
                    // TODO : api 추가 후 viewmodel로 구현예정
                }
            )
        },

        content = {
            LazyColumn (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(it)
                    .background(HolixTheme.colors.gray01),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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
                        item {
                            val dateTime = LocalDateTime.parse(state.data[0].createdAt.toString())

                            val dateFormatter =
                                DateTimeFormatter.ofPattern("yyyy년 M월 d일 E요일", Locale.KOREAN)

                            val dateText = dateTime.format(dateFormatter)

                            Text(
                                text = dateText,
                                style = HolixTheme.typography.label3R11,
                                color = HolixTheme.colors.gray06,
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                textAlign = TextAlign.Center
                            )
                        }

                        items(state.data) {chattingData ->
                            if (chattingData.chattingType == ChattingType.SYSTEM) {
                                ChattingSystemMessage(message = chattingData.contents)
                            } else {
                                ChattingItem(
                                    nickname = chattingData.userName,
                                    introduction = chattingData.introduction,
                                    profileUrl = chattingData.imageUrl,
                                    chat = chattingData.contents,
                                    likes = chattingData.likes,
                                    isMine = chattingData.isMine,
                                    date = chattingData.createdAt.toString(),
                                ) {

                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ChattingScreenPreview() {

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


    HolixAndroidTheme{
        ChattingScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            state = UiState.Success(dummyList)
        )
    }
}