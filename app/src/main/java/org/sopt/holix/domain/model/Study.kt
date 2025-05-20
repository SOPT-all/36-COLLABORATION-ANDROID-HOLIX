package org.sopt.holix.domain.model

import org.sopt.holix.presentation.home.model.StudyDataUiModel
import org.sopt.holix.presentation.home.model.StudyUiModel
import org.sopt.holix.presentation.home.model.TagUiModel
import org.sopt.holix.presentation.mapper.toUiModel

data class Study(
    val id: Int,
    val title: String,
    val leader: String,
    val price: String,
    val imageUrl: String,
    val tags: List<Tag>,
    val category: String
)

data class Tag(
    val name: String,
    val color: String
)

data class StudyData(
    val passionateStudies: List<Study>,
    val insightStudies: List<Study>,
    val newStudies: List<Study>,
    val recommendStudies: List<Study>,
    val freeStudies: List<Study>
)