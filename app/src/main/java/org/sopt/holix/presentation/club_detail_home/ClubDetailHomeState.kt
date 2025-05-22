package org.sopt.holix.presentation.club_detail_home

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.holix.core.util.UiState
import org.sopt.holix.domain.model.ClubDetailEntity

data class ClubDetailHomeState(
    val uiState: UiState<ClubDetailEntity> = UiState.Loading
)