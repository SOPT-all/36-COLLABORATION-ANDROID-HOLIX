package org.sopt.holix.data.dto.response.chatting

import kotlinx.serialization.SerialName

data class ChattingBaseResponse<T> (
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<T>
)