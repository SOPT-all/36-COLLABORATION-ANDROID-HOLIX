package org.sopt.holix.data.dto.response.home

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import org.sopt.holix.domain.model.home.Study
import org.sopt.holix.domain.model.home.StudyEntity
import org.sopt.holix.domain.model.home.Tag

@Serializable
data class HomeResponseDto(
    @SerialName("passionateStudies")
    val passionateStudies: List<StudyDto>,
    @SerialName("insightStudies")
    val insightStudies: List<StudyDto>,
    @SerialName("newStudies")
    val newStudies: List<StudyDto>,
    @SerialName("recommendedStudies")
    val recommendedStudies: List<StudyDto>,
    @SerialName("freeStudies")
    val freeStudies: List<StudyDto>
) {
    fun toEntity(): StudyEntity = StudyEntity(
        passionateStudies = passionateStudies.map { it.toEntity() },
        insightStudies = insightStudies.map { it.toEntity() },
        newStudies = newStudies.map { it.toEntity() },
        recommendedStudies = recommendedStudies.map { it.toEntity() },
        freeStudies = freeStudies.map { it.toEntity() }
    )
}

@Serializable
data class StudyDto(
    @SerialName("studyId")
    val studyId: Int,
    @SerialName("studyTitle")
    val studyTitle: String,
    @SerialName("studyLeader")
    val studyLeader: String,
    @SerialName("price")
    val price: String,
    @SerialName("url")
    val url: String,
    @SerialName("tags")
    val tags: List<TagDto>,
    @SerialName("category")
    val category: String
) {
    fun toEntity(): Study = Study(
        id = studyId,
        title = studyTitle,
        leader = studyLeader,
        price = price,
        imageUrl = url,
        tags = tags.map { it.toEntity() },
        category = category
    )
}

@Serializable
data class TagDto(
    @SerialName("tagId") val tagId: Int,
    @SerialName("tagName") val tagName: String,
    @SerialName("tagColor") val tagColor: String
) {
    fun toEntity(): Tag = Tag(
        name = tagName,
        color = tagColor
    )
}

