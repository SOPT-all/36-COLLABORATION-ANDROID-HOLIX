package org.sopt.holix.domain.repository

import org.sopt.holix.domain.model.home.StudyData

interface HomeRepository {
    suspend fun getHomeData(): StudyData
}