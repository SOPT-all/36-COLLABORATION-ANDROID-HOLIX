package org.sopt.holix.data.dto.response.myclub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.holix.domain.model.MyClubEntity

@Serializable
data class MyClubResponseDto(
    @SerialName("clubs")
    val clubs: List<Club>
)

@Serializable
data class Club(
    @SerialName("clubId")
    val clubId: Long,
    @SerialName("isPinned")
    val isPinned: Boolean,
    @SerialName("participants")
    val participants: String,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
) {
    fun toEntity() = MyClubEntity(
        clubId = clubId,
        isPinned = isPinned,
        participants = participants,
        title = title,
        url = url
    )
}
