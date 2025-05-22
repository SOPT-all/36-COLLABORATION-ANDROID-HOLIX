package org.sopt.holix.data.repository

import org.sopt.holix.data.datasource.MainDataSource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
) {

}
