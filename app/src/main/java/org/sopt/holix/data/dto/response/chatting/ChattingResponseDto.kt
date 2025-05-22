package org.sopt.holix.data.dto.response.chatting

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity
import org.sopt.holix.domain.model.chatting.ChattingType

@Serializable
data class ChattingResponseDto(
    @SerialName("chattingId")
    val chattingId : Long,
    @SerialName("clubId")
    val clubId : Long,
    @SerialName("userName")
    val userName : String,
    @SerialName("introduction")
    val introduction : String,
    @SerialName("imageUrl")
    val imageUrl : String,
    @SerialName("contents")
    val contents : String,
    @SerialName("likes")
    val likes : Int,
    @SerialName("chattingType")
    val chattingType : String,
    @SerialName("isMine")
    val isMine : Boolean,
    @SerialName("createdAt")
    val createdAt : String,
) {
    fun toEntity() = ChattingListDataEntity(
        chattingId = chattingId,
        clubId = clubId,
        userName = userName,
        introduction = introduction,
        imageUrl = imageUrl,
        contents = contents,
        likes = likes,
        chattingType = if (chattingType == "USER") {
            ChattingType.USER
        } else {
            ChattingType.SYSTEM
        },
        isMine = isMine,
        createdAt = createdAt
    )
}