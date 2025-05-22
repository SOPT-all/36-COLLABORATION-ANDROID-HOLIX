package org.sopt.holix.presentation.myclub

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.holix.core.util.UiState
import org.sopt.holix.domain.model.MyClubEntity
import org.sopt.holix.domain.model.RecommendClubEntity


data class MyClubState(
    val uiState: UiState<PersistentList<MyClubEntity>> = UiState.Loading,
    val recommendUiState: PersistentList<RecommendClubEntity> = persistentListOf(),
    val selectedTab: Int = 0
)