package com.github.linzhengen.presenter.handler

import com.github.linzhengen.usecase.UserUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class UserHandler(private val userUseCase: UserUseCase) {
    suspend fun post(call: ApplicationCall) {
        val name = call.parameters["name"]
        name?.let {
            var id = userUseCase.createUser(it)
            call.respondText("created user: ${id.toString()}")
            return
        }
        call.respondText("failed create user")
    }

    suspend fun get(call: ApplicationCall) {
        var id = call.parameters["id"] as UUID
        id?.let {
            var user = userUseCase.findUser(id)
            call.respondText("find user: ${user.toString()}")
            return
        }
        call.respondText("user not found")
    }
}