package de.sambalmueslie.clan


import de.sambalmueslie.clan.api.PlayerAPI
import de.sambalmueslie.clan.api.PlayerChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication

@Controller("/api/player")
class PlayerController(private val service: PlayerService) : PlayerAPI {

    @Post
    override fun create(auth: Authentication, @Body request: PlayerChangeRequest) = service.create(auth, request)

    @Put("/{id}")
    override fun update(auth: Authentication, @PathVariable id: Long, @Body request: PlayerChangeRequest) = service.update(auth, id, request)

    @Get("/{id}")
    override fun get(auth: Authentication, @PathVariable id: Long) = service.get(auth, id)

    @Get
    override fun getAll(auth: Authentication, pageable: Pageable) = service.getAll(auth, pageable)

    @Delete("/{id}")
    override fun delete(auth: Authentication, @PathVariable id: Long) = service.delete(auth, id)
}
