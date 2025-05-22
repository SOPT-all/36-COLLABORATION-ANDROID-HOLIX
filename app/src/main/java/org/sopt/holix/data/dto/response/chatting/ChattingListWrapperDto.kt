package org.sopt.holix.data.dto.response.chatting

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChattingListWrapperDto(
    @SerialName("chattingList")
    val chattingList: List<ChattingResponseDto>
)