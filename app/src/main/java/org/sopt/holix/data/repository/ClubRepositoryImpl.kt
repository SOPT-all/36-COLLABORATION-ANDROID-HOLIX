package org.sopt.holix.data.repository

import org.sopt.holix.data.datasource.ClubDataSource
import org.sopt.holix.data.dto.response.chatting.ChattingPostResponseDto
import org.sopt.holix.domain.model.chatting.ChattingDataEntity
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity
import org.sopt.holix.domain.repository.ClubRepository
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(
    private val clubDataSource: ClubDataSource
) : ClubRepository {
    override suspend fun getChattingList(clubId: Long): Result<List<ChattingListDataEntity>> = runCatching {
        clubDataSource.getChattingList(clubId).data.chattingList.map { it.toEntity() }
    }

    override suspend fun postChatting(clubId: Long, chattingEntity: ChattingDataEntity): Result<ChattingPostResponseDto> = runCatching {
        clubDataSource.postChatting(clubId, chattingEntity)
    }
}
