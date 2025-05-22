package org.sopt.holix.data.repository

import org.sopt.holix.data.datasource.ClubDataSource
import org.sopt.holix.domain.model.MyClubEntity
import org.sopt.holix.domain.repository.ClubRepository
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(
    private val clubDataSource: ClubDataSource
): ClubRepository {
    override suspend fun getMyClubsList(): Result<List<MyClubEntity>> = runCatching {
        clubDataSource.getMyClubsList().data.clubs.map { it.toEntity() }
    }
}
