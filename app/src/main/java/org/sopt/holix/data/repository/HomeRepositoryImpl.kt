package org.sopt.holix.data.repository

import org.sopt.holix.data.service.HomeService
import org.sopt.holix.domain.model.home.StudyData
import org.sopt.holix.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository {
    override suspend fun getHomeData(): StudyData {
        val response = homeService.getHomeData()

        return response.data.toDomainModel()
    }
}