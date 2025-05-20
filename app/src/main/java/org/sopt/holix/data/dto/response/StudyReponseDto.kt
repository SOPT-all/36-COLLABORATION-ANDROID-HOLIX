package org.sopt.holix.data.dto.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class StudyResponseDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: StudyDataDto
)

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
)

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
)
@Serializable
data class TagDto(
    @SerialName("tagId")
    val tagId: Int,
    @SerialName("tagName")
    val tagName: String,
    @SerialName("tagColor")
    val tagColor: String
)
