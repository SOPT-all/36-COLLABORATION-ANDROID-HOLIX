package org.sopt.holix.data.repository

import org.sopt.holix.data.datasource.HomeDataSource
import org.sopt.holix.data.service.HomeService
import org.sopt.holix.domain.model.home.StudyEntity
import org.sopt.holix.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getHomeData(): StudyEntity {
        val response = homeDataSource.getHomeData()

        return response.data.toEntity()
    }
}