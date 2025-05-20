package org.sopt.holix.domain.model

data class MyClubEntity(
    val clubId: Long,
    val title: String,
    val isPinned: Boolean,
    val url: String,
    val participants: String
)
