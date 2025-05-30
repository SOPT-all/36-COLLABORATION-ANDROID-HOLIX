package org.sopt.holix.domain.model.chatting


data class ChattingListDataEntity(
    val chattingId : Long,
    val clubId : Long,
    val userName : String,
    val introduction : String,
    val imageUrl : String,
    val contents : String,
    val likes : Int,
    val chattingType : ChattingType,
    val isMine : Boolean,
    val createdAt : String,
)
