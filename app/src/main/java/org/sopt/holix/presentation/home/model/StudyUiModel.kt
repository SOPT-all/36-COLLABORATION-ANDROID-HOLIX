package org.sopt.holix.presentation.home.model

data class StudyUiModel(
    val studyId: Int,
    val studyTitle: String,
    val studyLeader: String,
    val price: String,
    val imageUrl: String,
    val tags: List<TagUiModel>,
    val category: String
)