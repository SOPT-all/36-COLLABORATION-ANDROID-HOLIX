package org.sopt.holix.domain.model

data class Study(
    val id: Int,
    val title: String,
    val leader: String,
    val price: String,
    val imageUrl: String,
    val tags: List<Tag>,
    val category: String
)
