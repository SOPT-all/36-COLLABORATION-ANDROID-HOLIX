package org.sopt.holix.domain.model

data class ClubDetailEntity(
    val clubId: Long,
    val title: String,
    val participants: String,
    val url : String,
    val notice: String
)
