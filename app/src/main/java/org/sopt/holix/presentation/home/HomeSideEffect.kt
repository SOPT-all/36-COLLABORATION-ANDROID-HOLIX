package org.sopt.holix.presentation.home


sealed interface HomeSideEffect {
    data class ShowSnackBar(val message: String) : HomeSideEffect
}