package org.sopt.holix.data.datasource

import org.sopt.holix.data.dto.request.toDto
import org.sopt.holix.data.service.ClubService
import org.sopt.holix.domain.model.chatting.ChattingDataEntity
import javax.inject.Inject

class ClubDataSource @Inject constructor (
    private val clubService: ClubService
) {
    suspend fun getChattingList(clubId: Long) = clubService.getChattingList(clubId)

    suspend fun postChatting(clubId: Long, chattingDataEntity: ChattingDataEntity) = clubService.postChatting(clubId, chattingDataEntity.toDto())
}
