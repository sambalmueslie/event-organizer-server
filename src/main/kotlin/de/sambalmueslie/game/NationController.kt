package de.sambalmueslie.game


import de.sambalmueslie.event.api.TeamChangeRequest
import de.sambalmueslie.game.api.MapAPI
import de.sambalmueslie.game.api.MapChangeRequest
import de.sambalmueslie.game.api.NationAPI
import de.sambalmueslie.game.api.NationChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication

@Controller("/api/game/nation")
class NationController(private val service: NationService) : NationAPI {
    @Post
    override fun create(auth: Authentication, @Body request: NationChangeRequest) = service.create(auth, request)

    @Put("/{id}")
    override fun update(auth: Authentication, @PathVariable id: Long, @Body request: NationChangeRequest) = service.update(auth, id, request)

    @Get("/{id}")
    override fun get(auth: Authentication, @PathVariable id: Long) = service.get(auth, id)

    @Get
    override fun getAll(auth: Authentication, pageable: Pageable) = service.getAll(auth, pageable)

    @Delete("/{id}")
    override fun delete(auth: Authentication, @PathVariable id: Long) = service.delete(auth, id)


}
