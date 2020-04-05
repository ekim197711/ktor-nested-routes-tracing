package com.example

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        trace { application.log.trace(it.buildText()) }
        route("/space") {
            header("somesecret", "qwerty") {
                route("/planet/{planet?}") {
                    handle {
                        val planet = call.parameters.get(("planet"))
                        call.respondText { "Yiihaaa you just landend on $planet" }
                    }
                }
            }

            route("/saturn") {
                param("person") {
                    handle {
                        val person = call.parameters.get("person")

                        call.respondText { "Dear $person, Yes you have landed on Saturn" }
                    }

                    get("/weather") {
                        call.respondText { "Windy and maybe stay indoor" }
                    }
                }
            }
        }
    }
}

