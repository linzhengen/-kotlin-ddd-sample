package com.github.linzhengen.presenter.handler

import io.ktor.server.application.*
import io.ktor.server.response.*

class HealthCheckHandler {
    suspend fun get(call: ApplicationCall) {
        call.respondText("ok")
    }
}