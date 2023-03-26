package com.github.linzhengen.usecase

import com.github.linzhengen.domain.user.UserRepository
import com.github.linzhengen.domain.user.User
import com.github.linzhengen.domain.user.UserNotFoundException
import java.lang.RuntimeException
import java.util.UUID

class UserUseCase(private val userRepository: UserRepository) {
    suspend fun createUser(name: String): UUID {
        return userRepository.create(name).id
    }

    suspend fun findUser(id: String): User? {
        try {
            return userRepository.findById(UUID.fromString(id))
        } catch (e: NoSuchElementException) {
            throw UserNotFoundException("user not found")
        } catch (e: Exception) {
            throw RuntimeException("internal server error")
        }
    }
}