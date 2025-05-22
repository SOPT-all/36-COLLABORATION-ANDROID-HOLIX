package org.sopt.holix.data.dto.response.home

data class HomeBaseResponse<T>(
    val code: Int,
    val message: String,
    val data: T
)
