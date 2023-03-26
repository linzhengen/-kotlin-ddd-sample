package com.github.linzhengen.domain.user

import java.util.UUID

interface UserRepository {
    suspend fun findById(id: UUID): User?
    suspend fun create(name: String): User
    suspend fun delete(id: UUID)
}