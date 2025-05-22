package org.sopt.holix.data.repository.chatting

import org.sopt.holix.data.datasource.ChattingDataSource
import org.sopt.holix.data.dto.request.ChattingRequestDto
import org.sopt.holix.data.dto.response.chatting.ChattingListWrapperDto
import org.sopt.holix.data.dto.response.chatting.ChattingPostResponseDto
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity
import org.sopt.holix.domain.repository.ChattingRepository
import javax.inject.Inject

class ChattingRepositoryImpl @Inject constructor(
    private val chattingDataSource: ChattingDataSource
) : ChattingRepository {
    override suspend fun getChattingList(clubId: Long): Result<ChattingListWrapperDto> = runCatching {
        chattingDataSource.getChattingList(clubId).data
    }

    override suspend fun postChatting(clubId: Long, chattingRequestDto: ChattingRequestDto): Result<ChattingPostResponseDto> = runCatching {
        chattingDataSource.postChatting(clubId, chattingRequestDto)
    }
}