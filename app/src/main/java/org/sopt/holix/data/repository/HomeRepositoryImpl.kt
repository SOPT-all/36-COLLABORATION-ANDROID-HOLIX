package org.sopt.holix.data.repository

import org.sopt.holix.data.datasource.HomeDataSource
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity
import org.sopt.holix.domain.model.home.StudyEntity
import org.sopt.holix.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getHomeData(): Result<StudyEntity> = runCatching {
        homeDataSource.getHomeData().data.toEntity()
    }
}