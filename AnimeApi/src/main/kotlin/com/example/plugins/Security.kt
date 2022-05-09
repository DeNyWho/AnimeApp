package com.example.plugins

import com.example.authentication.JwtService
import com.example.repository.Repo
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.sessions.*

fun Application.configureSecurity() {
    data class MySessions(val count: Int = 0)
    val jwtService = JwtService()
    val db = Repo()

    install(Sessions) {
        cookie<MySessions>("MY_SESSIONS") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(Authentication) {

        jwt("jwt"){
            verifier(jwtService.verifier)
            realm = "AnimeServer"
            validate {
                val payload = it.payload
                val email = payload.getClaim("email").asString()
                val user = db.findByUserEmail(email)
                user
            }
        }

    }

}
