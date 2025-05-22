package org.sopt.holix.data.datasource

import org.sopt.holix.data.service.ClubService
import javax.inject.Inject

class ClubDataSource @Inject constructor (
    private val clubService: ClubService
) {
    suspend fun getMyClubsList() = clubService.getMyClubList()
}
