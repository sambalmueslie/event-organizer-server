package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.event.api.TeamChangeRequest
import de.sambalmueslie.eventorg.game.api.MapAPI
import de.sambalmueslie.eventorg.game.api.MapChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication

@Controller("/api/game/map")
class MapController(private val service: MapService) : MapAPI {
    @Post
    override fun create(auth: Authentication, @Body request: MapChangeRequest) = service.create(auth, request)

    @Put("/{id}")
    override fun update(auth: Authentication, @PathVariable id: Long, @Body request: MapChangeRequest) = service.update(auth, id, request)

    @Get("/{id}")
    override fun get(auth: Authentication, @PathVariable id: Long) = service.get(auth, id)

    @Get
    override fun getAll(auth: Authentication, pageable: Pageable) = service.getAll(auth, pageable)

    @Delete("/{id}")
    override fun delete(auth: Authentication, @PathVariable id: Long) = service.delete(auth, id)


}
