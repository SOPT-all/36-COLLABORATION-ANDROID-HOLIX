package org.sopt.holix.data.dto.response

data class StudyResponseDto(
    val studyId: Int,
    val studyTitle: String,
    val studyLeader: String,
    val price: String,
    val url: String,
    val tags: List<TagResponseDto>,
    val category: String
)