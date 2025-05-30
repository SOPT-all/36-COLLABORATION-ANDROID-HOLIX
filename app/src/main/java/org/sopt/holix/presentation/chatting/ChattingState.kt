package org.sopt.holix.presentation.chatting

import kotlinx.collections.immutable.PersistentList
import org.sopt.holix.core.util.UiState
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity

data class ChattingState(
    val uiState: UiState<PersistentList<ChattingListDataEntity>> = UiState.Loading,
    val chattingState : UiState<String> = UiState.Loading, //response로 받을 데이터가 없음. post 메세지 관리용
    val chattingText : String = "",
    val isSendChatting : Boolean = false
)