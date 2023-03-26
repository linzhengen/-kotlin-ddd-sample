package com.github.linzhengen.presenter.router

import com.github.linzhengen.infrastructure.user.UserRepositoryImpl
import com.github.linzhengen.presenter.handler.HealthCheckHandler
import com.github.linzhengen.presenter.handler.UserHandler
import com.github.linzhengen.usecase.UserUseCase
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    val userRepository = UserRepositoryImpl()
    val userUseCase = UserUseCase(userRepository)
    routing {
        get("/") {
            HealthCheckHandler().get(call)
        }
        get("/users") {
            UserHandler(userUseCase).get(call)
        }
        post("/users") {
            UserHandler(userUseCase).post(call)
        }
    }
}
