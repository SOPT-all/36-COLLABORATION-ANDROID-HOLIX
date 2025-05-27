package org.sopt.holix.domain.repository

import org.sopt.holix.data.dto.response.chatting.ChattingPostResponseDto
import org.sopt.holix.domain.model.chatting.ChattingDataEntity
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity
import org.sopt.holix.domain.model.ClubDetailEntity
import org.sopt.holix.domain.model.MyClubEntity

interface ClubRepository {
    suspend fun getChattingList(clubId : Long): Result<List<ChattingListDataEntity>>

    suspend fun postChatting(clubId: Long, chattingEntity: ChattingDataEntity): Result<ChattingPostResponseDto>

    suspend fun getMyClubsList(): Result<List<MyClubEntity>>
        
    suspend fun getClubDetail(clubId: Long): Result<ClubDetailEntity>
}
