package org.sopt.holix.domain.repository

import org.sopt.holix.domain.model.ClubDetailEntity

interface ClubRepository {
    suspend fun getClubDetail(clubId: Long): Result<ClubDetailEntity>
}
