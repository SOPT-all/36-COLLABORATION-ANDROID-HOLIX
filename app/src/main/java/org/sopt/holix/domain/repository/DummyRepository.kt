package org.sopt.holix.domain.repository

import org.sopt.holix.domain.model.DummyUser

interface DummyRepository {
    suspend fun getDummyList(): Result<List<DummyUser>>
}