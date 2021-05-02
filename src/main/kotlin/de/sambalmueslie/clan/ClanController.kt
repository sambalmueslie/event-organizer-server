package de.sambalmueslie.clan


import io.micronaut.http.annotation.Controller

@Controller("/api/clan")
class ClanController(private val service: ClanService) {


}
