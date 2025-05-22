package org.sopt.holix.domain.repository

import org.sopt.holix.domain.model.chatting.ChattingListDataEntity

interface ChattingRepository {
    suspend fun getChattingList(clubId : Long): Result<List<ChattingListDataEntity>>
}