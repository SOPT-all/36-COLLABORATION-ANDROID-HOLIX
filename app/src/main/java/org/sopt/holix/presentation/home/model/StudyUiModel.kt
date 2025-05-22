package org.sopt.holix.presentation.home.model

import org.sopt.holix.domain.model.Tag

data class StudyUiModel(
    val studyId: Int,
    val studyTitle: String,
    val studyLeader: String,
    val price: String,
    val imageUrl: String,
    val tags: List<TagUiModel>,
    val category: String
)

data class StudyDataUiModel(
    val passionateStudies: List<StudyUiModel>,
    val insightStudies: List<StudyUiModel>,
    val newStudies: List<StudyUiModel>,
    val recommendStudies: List<StudyUiModel>,
    val freeStudies: List<StudyUiModel>
)

data class TagUiModel(
    val name: String,
    val color: String
) {
    fun Tag.toUiModel(): TagUiModel = TagUiModel(
        name = name,
        color = color
    )
}