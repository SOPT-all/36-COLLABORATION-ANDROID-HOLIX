package org.sopt.holix.presentation.home

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.holix.presentation.home.model.StudyUiModel

data class HomeState(
    val selectedTab: Int = 0,
    val search: String = "",
    val sections: PersistentList<List<StudyUiModel>> = persistentListOf()
)
