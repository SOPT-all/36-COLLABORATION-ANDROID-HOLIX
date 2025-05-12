package org.sopt.holix.data.repository

import org.sopt.holix.data.datasource.DummyDataSource
import org.sopt.holix.domain.model.DummyUser
import org.sopt.holix.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyDataSource: DummyDataSource
): DummyRepository {
    override suspend fun getDummyList(): Result<List<DummyUser>> = runCatching {
        dummyDataSource.getDummyList().data.map { it.toDomainModel() }
    }

}