package org.sopt.holix.presentation.club_detail_home

sealed interface ClubDetailHomeSideEffect {
    data class ShowSnackBar(val message: String) : ClubDetailHomeSideEffect
    object NavigateUp : ClubDetailHomeSideEffect
}