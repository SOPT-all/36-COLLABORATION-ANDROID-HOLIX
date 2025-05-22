package org.sopt.holix.data.repository.chatting

import org.sopt.holix.data.datasource.ChattingDataSource
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity
import org.sopt.holix.domain.repository.ChattingRepository
import javax.inject.Inject

class ChattingRepositoryImpl @Inject constructor(
    private val chattingDataSource: ChattingDataSource
) : ChattingRepository {
    override suspend fun getChattingList(clubId: Long): Result<List<ChattingListDataEntity>> = runCatching {
        chattingDataSource.getChattingList(clubId).data.map { it.toDomainModel() }
    }
}