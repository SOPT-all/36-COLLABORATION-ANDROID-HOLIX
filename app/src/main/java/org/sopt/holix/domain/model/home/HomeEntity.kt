package org.sopt.holix.domain.model.home

data class StudyEntity(
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
    val passionateStudies: List<StudyEntity>,
    val insightStudies: List<StudyEntity>,
    val newStudies: List<StudyEntity>,
    val recommendedStudies: List<StudyEntity>,
    val freeStudies: List<StudyEntity>
)