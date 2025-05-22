package org.sopt.holix.presentation.home

import org.sopt.holix.presentation.home.HomeSideEffect

sealed interface HomeSideEffect {
    data class ShowSnackBar(val message: String) : HomeSideEffect
    object NavigateUp : HomeSideEffect
    object NavigateNext : HomeSideEffect
}