package org.sopt.holix.presentation.chatting

import kotlinx.collections.immutable.PersistentList
import org.sopt.holix.core.util.UiState
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity

data class ChattingState(
    val uiState: UiState<PersistentList<ChattingListDataEntity>> = UiState.Loading,
    val chattingText : String = ""
)