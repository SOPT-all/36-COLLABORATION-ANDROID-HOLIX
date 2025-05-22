package org.sopt.holix.data.repository

import org.sopt.holix.data.datasource.ClubDataSource
import org.sopt.holix.data.dto.request.ChattingRequestDto
import org.sopt.holix.data.dto.response.chatting.ChattingListWrapperDto
import org.sopt.holix.data.dto.response.chatting.ChattingPostResponseDto
import org.sopt.holix.domain.repository.ClubRepository
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(
    private val clubDataSource: ClubDataSource
) : ClubRepository {
    override suspend fun getChattingList(clubId: Long): Result<ChattingListWrapperDto> = runCatching {
        clubDataSource.getChattingList(clubId).data
    }

    override suspend fun postChatting(clubId: Long, chattingRequestDto: ChattingRequestDto): Result<ChattingPostResponseDto> = runCatching {
        clubDataSource.postChatting(clubId, chattingRequestDto)
    }
}
