package org.sopt.holix.data.service

import org.sopt.holix.data.dto.response.BaseResponse
import org.sopt.holix.data.dto.response.home.HomeResponseDto
import retrofit2.http.GET

interface HomeService {
    @GET("/api/v1/main")
    suspend fun getHomeData(): BaseResponse<HomeResponseDto>
}
