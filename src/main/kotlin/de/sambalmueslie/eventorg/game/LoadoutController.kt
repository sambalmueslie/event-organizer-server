package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.game.api.LoadoutAPI
import de.sambalmueslie.eventorg.game.api.LoadoutChangeRequest
import de.sambalmueslie.eventorg.game.api.MapChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication

@Controller("/api/game/loadout")
class LoadoutController(private val service: de.sambalmueslie.eventorg.game.LoadoutService) : LoadoutAPI {
    @Post
    override fun create(auth: Authentication, @Body request: LoadoutChangeRequest) = service.create(auth, request)

    @Put("/{id}")
    override fun update(auth: Authentication, @PathVariable id: Long, @Body request: LoadoutChangeRequest) = service.update(auth, id, request)

    @Get("/{id}")
    override fun get(auth: Authentication, @PathVariable id: Long) = service.get(auth, id)

    @Get
    override fun getAll(auth: Authentication, pageable: Pageable) = service.getAll(auth, pageable)

    @Delete("/{id}")
    override fun delete(auth: Authentication, @PathVariable id: Long) = service.delete(auth, id)


}
