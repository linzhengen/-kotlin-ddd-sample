package com.github.linzhengen.presenter.handler

import com.github.linzhengen.usecase.UserUseCase
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserHandler(private val userUseCase: UserUseCase) {
    suspend fun post(call: ApplicationCall) {
        val params = call.receive<PostUserRequest>()
        params?.let {
            var id = userUseCase.createUser(it.name)
            call.respondText("created user: $id")
            return
        }
        call.respondText("failed create user")
    }

    suspend fun get(call: ApplicationCall) {
        var id = call.request.queryParameters["id"]

        id?.let {
            var user = userUseCase.findUser(id)
            call.respondText("find user: ${user.toString()}")
            return
        }
        call.respondText("user not found")
    }
}

data class PostUserRequest(var name: String) {
    init {
        validation()
    }

    private fun validation() {
        if (this.name == "") {
            throw BadRequestException("Invalid name")
        }
    }
}