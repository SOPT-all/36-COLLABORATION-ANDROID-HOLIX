package org.sopt.holix.data.datasource

import org.sopt.holix.data.service.ClubService
import org.sopt.holix.data.service.DummyService
import javax.inject.Inject

class ClubDataSource @Inject constructor (
    private val clubService: ClubService
) {
    suspend fun getClubDetail(clubId: Long) = clubService.getClubDetail(clubId)
}

