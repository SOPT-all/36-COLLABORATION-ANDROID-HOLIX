package org.sopt.holix.data.repository

import org.sopt.holix.data.service.HomeService
import org.sopt.holix.domain.model.home.StudyEntity
import org.sopt.holix.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository {
    override suspend fun getHomeData(): StudyEntity {
        val response = homeService.getHomeData()

        return response.data.toEntity()
    }
}