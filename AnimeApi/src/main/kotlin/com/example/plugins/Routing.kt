package com.example.plugins

import com.example.authentication.JwtService
import com.example.authentication.hash
import com.example.repository.Repo
import com.example.routes.userRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    fun hashFunction(s: String) = hash(s)
    val jwtService = JwtService()
    val db = Repo()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        val hashFun = { s: String -> hash(s) }
        userRoutes(db, jwtService, hashFun)

    }
}
