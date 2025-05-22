package org.sopt.holix.presentation.home

import org.sopt.holix.presentation.club_detail_home.ClubDetailHomeSideEffect


sealed interface HomeSideEffect {
    data class ShowSnackBar(val message: String) : HomeSideEffect
}