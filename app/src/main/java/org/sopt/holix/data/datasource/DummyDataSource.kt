package org.sopt.holix.data.datasource

import org.sopt.holix.data.service.DummyService
import javax.inject.Inject

class DummyDataSource @Inject constructor (
    private val dummyService: DummyService
) {
    suspend fun getDummyList() = dummyService.getDummyLists()
}
