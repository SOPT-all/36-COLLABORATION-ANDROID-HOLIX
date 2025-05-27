package org.sopt.holix.data.service

import org.sopt.holix.data.dto.request.ChattingRequestDto
import org.sopt.holix.data.dto.response.BaseResponse
import org.sopt.holix.data.dto.response.chatting.ChattingListDto
import org.sopt.holix.data.dto.response.chatting.ChattingPostResponseDto
import org.sopt.holix.data.dto.response.myclub.MyClubResponseDto
import org.sopt.holix.data.dto.response.ClubDetailResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClubService {
    @GET("api/v1/club/{clubId}/chat")
    suspend fun getChattingList(
        @Path(value = "clubId") clubId : Long
    ) : BaseResponse<ChattingListDto>


    @POST("api/v1/club/{clubId}/chat")
    suspend fun postChatting(
        @Path(value = "clubId") clubId : Long,
        @Body chattingRequest: ChattingRequestDto
    ) : ChattingPostResponseDto
  
    @GET("api/v1/club")
    suspend fun getMyClubList(): BaseResponse<MyClubResponseDto>

    @GET("api/v1/club/{clubId}")
    suspend fun getClubDetail(@Path("clubId") clubId: Long): BaseResponse<ClubDetailResponseDto>
}
