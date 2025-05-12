package org.sopt.holix.presentation.dummy

import kotlinx.collections.immutable.PersistentList
import org.sopt.holix.core.util.UiState
import org.sopt.holix.domain.model.DummyUser

data class DummyState(
    val uiState: UiState<PersistentList<DummyUser>> = UiState.Loading
)