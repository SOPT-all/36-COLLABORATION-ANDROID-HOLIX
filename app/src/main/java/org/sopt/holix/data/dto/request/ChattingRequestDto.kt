package org.sopt.holix.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.holix.domain.model.chatting.ChattingDataEntity

@Serializable
data class ChattingRequestDto(
    @SerialName("contents")
    val contents : String
) {
    fun toEntity() = ChattingDataEntity(
        contents = contents
    )
}