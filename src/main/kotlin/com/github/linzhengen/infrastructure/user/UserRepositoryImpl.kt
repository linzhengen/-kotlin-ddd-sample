package com.github.linzhengen.infrastructure.user

import com.github.linzhengen.domain.user.User
import com.github.linzhengen.domain.user.UserRepository
import com.github.linzhengen.infrastructure.user.persistence.UserDao
import java.util.*

class UserRepositoryImpl : UserRepository {
    override suspend fun findById(id: UUID): User? {
        return UserDao.find(id)
    }

    override suspend fun create(name: String): User {
        return User(UserDao.create(name), name)
    }

    override suspend fun delete(id: UUID) {
        UserDao.delete(id)
    }

}