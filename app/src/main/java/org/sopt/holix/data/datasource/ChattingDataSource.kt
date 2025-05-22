package org.sopt.holix.data.datasource

import org.sopt.holix.data.dto.request.ChattingRequestDto
import org.sopt.holix.data.service.ChattingService
import javax.inject.Inject

class ChattingDataSource @Inject constructor(
    private val chattingService: ChattingService
) {
    suspend fun getChattingList(clubId: Long) = chattingService.getChattingList(clubId)

    suspend fun postChatting(clubId: Long, chattingRequestDto: ChattingRequestDto) = chattingService.postChatting(clubId, chattingRequestDto)
}