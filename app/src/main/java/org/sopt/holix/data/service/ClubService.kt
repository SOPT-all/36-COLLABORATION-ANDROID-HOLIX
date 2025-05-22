package org.sopt.holix.data.service

import org.sopt.holix.data.dto.response.BaseResponse
import org.sopt.holix.data.dto.response.ClubDetailResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ClubService {
    @GET("api/v1/club/{clubId}")
    suspend fun getClubDetail(@Path("clubId") clubId: Long): BaseResponse<ClubDetailResponseDto>
}
