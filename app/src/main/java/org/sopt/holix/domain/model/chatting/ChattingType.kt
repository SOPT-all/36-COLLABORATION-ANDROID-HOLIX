package org.sopt.holix.domain.model.chatting

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ChattingType {
    @SerialName("USER") USER,
    @SerialName("SYSTEM") SYSTEM
}