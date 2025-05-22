package org.sopt.holix.presentation.chatting

sealed class ChattingSideEffect {
    data class ShowSnackBar(val message: String) : ChattingSideEffect()
    data object NavigateUp: ChattingSideEffect()
    data object NavigateNext: ChattingSideEffect()

}