package org.sopt.holix.data.dto.response.home

import kotlinx.serialization.SerialName

data class HomeBaseResponse<T>(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: T
)
