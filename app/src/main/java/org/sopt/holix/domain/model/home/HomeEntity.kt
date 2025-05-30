package org.sopt.holix.domain.model.home

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

data class StudyEntity(
    val passionateStudies: List<Study>,
    val insightStudies: List<Study>,
    val newStudies: List<Study>,
    val recommendedStudies: List<Study>,
    val freeStudies: List<Study>
)