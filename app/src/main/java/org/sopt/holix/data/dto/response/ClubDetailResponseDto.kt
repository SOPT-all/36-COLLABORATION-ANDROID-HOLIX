package org.sopt.holix.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.holix.domain.model.ClubDetailEntity

@Serializable
data class ClubDetailResponseDto(
    @SerialName("clubId")
    val clubId: Long,
    @SerialName("notice")
    val notice: String,
    @SerialName("participants")
    val participants: String,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
){//dto가 entity로 바뀌는 부분
    fun toEntity() = ClubDetailEntity(
        clubId = clubId,
        notice = notice,
        participants = participants,
        title = title,
        url = url
    )
}


