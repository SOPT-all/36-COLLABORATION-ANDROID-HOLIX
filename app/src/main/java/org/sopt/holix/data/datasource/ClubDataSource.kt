package org.sopt.holix.data.datasource

import org.sopt.holix.data.dto.request.ChattingRequestDto
import org.sopt.holix.data.service.ClubService
import javax.inject.Inject

class ClubDataSource @Inject constructor (
    private val clubService: ClubService
) {
    suspend fun getChattingList(clubId: Long) = clubService.getChattingList(clubId)

    suspend fun postChatting(clubId: Long, chattingRequestDto: ChattingRequestDto) = clubService.postChatting(clubId, chattingRequestDto)
}
