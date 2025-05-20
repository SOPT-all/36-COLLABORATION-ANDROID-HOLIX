package org.sopt.holix.data.home.model

data class StudyResponseDto(
    val id: Int,
    val title: String,
    val leader: String,
    val price: String,
    val imageUrl: String,
    val tags: List<String>,
    val category: String
)