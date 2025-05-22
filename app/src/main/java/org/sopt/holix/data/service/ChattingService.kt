package org.sopt.holix.data.service

import org.sopt.holix.data.dto.response.chatting.ChattingBaseResponse
import org.sopt.holix.data.dto.response.chatting.ChattingResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ChattingService {
    @GET("/club/{clubId}/chat")
    suspend fun getChattingList(
        @Path(value = "clubId") clubId : Long
    ) : ChattingBaseResponse<ChattingResponseDto>
}