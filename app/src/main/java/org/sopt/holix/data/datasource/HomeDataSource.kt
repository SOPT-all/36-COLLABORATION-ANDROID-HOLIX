package org.sopt.holix.data.datasource

import org.sopt.holix.data.dto.response.home.HomeResponseDto
import org.sopt.holix.data.service.HomeService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val homeService: HomeService
) {
    suspend fun getHomeData(): HomeResponseDto {
        return homeService.getHomeData()
    }
}
