package org.sopt.holix.domain.model

data class RecommendClubEntity(
    val title: String,
    val clubMaster: String,
    val participants: String,
    val isRecruiting: Boolean,
    val imageRes: Int
)
