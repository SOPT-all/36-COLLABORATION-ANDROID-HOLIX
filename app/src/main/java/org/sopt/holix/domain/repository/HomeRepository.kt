package org.sopt.holix.domain.repository

import org.sopt.holix.domain.model.home.Study
import org.sopt.holix.domain.model.home.StudyEntity

interface HomeRepository {
    suspend fun getHomeData(): StudyEntity
}