package org.sopt.holix.presentation.myclub

sealed class MyClubSideEffect {
    data class ShowSnackBar(val message: String) : MyClubSideEffect()
    data object NavigateUp: MyClubSideEffect()
    data class NavigateClubDetailHome(val clubId: Long): MyClubSideEffect()
}
