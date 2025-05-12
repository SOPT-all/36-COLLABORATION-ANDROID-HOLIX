package org.sopt.holix.presentation.dummy

sealed class DummySideEffect {
    data class ShowSnackBar(val message: String) : DummySideEffect()
    data object NavigateUp: DummySideEffect()
    data object NavigateNext: DummySideEffect()

}