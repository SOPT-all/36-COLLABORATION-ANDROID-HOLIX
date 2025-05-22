package org.sopt.holix.domain.repository

import org.sopt.holix.domain.model.MyClubEntity

interface ClubRepository {
    suspend fun getMyClubsList(): Result<List<MyClubEntity>>
}
