package org.sopt.holix.domain.repository

import org.sopt.holix.domain.model.ClubDetailEntity
import org.sopt.holix.domain.model.MyClubEntity

interface ClubRepository {
    suspend fun getMyClubsList(): Result<List<MyClubEntity>>
    suspend fun getClubDetail(clubId: Long): Result<ClubDetailEntity>
}
