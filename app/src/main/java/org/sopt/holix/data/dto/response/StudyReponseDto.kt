package org.sopt.holix.data.dto.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import org.sopt.holix.domain.model.Study
import org.sopt.holix.domain.model.StudyData
import org.sopt.holix.domain.model.Tag

@Serializable
data class StudyResponseDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: StudyDataDto
) {
    fun toDomainModel(): StudyData = data.toDomainModel()
}

@Serializable
data class StudyDataDto(
    @SerialName("passionateStudies")
    val passionateStudies: List<StudyDto>,
    @SerialName("insightStudies")
    val insightStudies: List<StudyDto>,
    @SerialName("newStudies")
    val newStudies: List<StudyDto>,
    @SerialName("recommendStudies")
    val recommendStudies: List<StudyDto>,
    @SerialName("freeStudies")
    val freeStudies: List<StudyDto>
) {
    fun toDomainModel(): StudyData = StudyData(
        passionateStudies = passionateStudies.map { it.toDomainModel() },
        insightStudies = insightStudies.map { it.toDomainModel() },
        newStudies = newStudies.map { it.toDomainModel() },
        recommendStudies = recommendStudies.map { it.toDomainModel() },
        freeStudies = freeStudies.map { it.toDomainModel() }
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
    fun toDomainModel(): Study = Study(
        id = studyId,
        title = studyTitle,
        leader = studyLeader,
        price = price,
        imageUrl = url,
        tags = tags.map { it.toDomainModel() },
        category = category
    )
}

@Serializable
data class TagDto(
    @SerialName("tagId")
    val tagId: Int,
    @SerialName("tagName")
    val tagName: String,
    @SerialName("tagColor")
    val tagColor: String
) {
    fun toDomainModel(): Tag = Tag(
        name = tagName,
        color = tagColor
    )
}
