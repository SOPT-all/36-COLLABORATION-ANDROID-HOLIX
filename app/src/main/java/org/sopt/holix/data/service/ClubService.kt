package org.sopt.holix.data.service

import org.sopt.holix.data.dto.response.BaseResponse
import org.sopt.holix.data.dto.response.myclub.MyClubResponseDto
import retrofit2.http.GET

interface ClubService {
    @GET("api/v1/club")
    suspend fun getMyClubList(): BaseResponse<MyClubResponseDto>
}
