package org.sopt.holix.presentation.home

import kotlinx.collections.immutable.PersistentList
import org.sopt.holix.core.util.UiState
import org.sopt.holix.domain.model.home.Study
import org.sopt.holix.domain.model.home.StudyEntity

data class HomeState(
    val selectedTab: Int = 0,
    val search: String = "",
    val uiState: UiState<PersistentList<List<Study>>> = UiState.Loading
)
