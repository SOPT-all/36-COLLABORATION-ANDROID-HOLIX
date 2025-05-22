package org.sopt.holix.domain.repository

import org.sopt.holix.data.dto.request.ChattingRequestDto
import org.sopt.holix.data.dto.response.chatting.ChattingListWrapperDto
import org.sopt.holix.data.dto.response.chatting.ChattingPostResponseDto

interface ClubRepository {
    suspend fun getChattingList(clubId : Long): Result<ChattingListWrapperDto>

    suspend fun postChatting(clubId: Long, chattingRequestDto: ChattingRequestDto): Result<ChattingPostResponseDto>
}
