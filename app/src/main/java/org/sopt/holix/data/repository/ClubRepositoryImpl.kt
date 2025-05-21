package org.sopt.holix.data.repository

import org.sopt.holix.data.datasource.ClubDataSource
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(
    private val clubDataSource: ClubDataSource
) {

}
