package de.sambalmueslie.eventorg.event


import de.sambalmueslie.eventorg.event.api.EventAPI
import de.sambalmueslie.eventorg.event.api.EventChangeRequest
import de.sambalmueslie.eventorg.event.api.SquadAPI
import de.sambalmueslie.eventorg.event.api.SquadChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication

@Controller("/api/squad")
class SquadController(private val service: SquadService) : SquadAPI {

    @Post
    override fun create(auth: Authentication, @Body request: SquadChangeRequest) = service.create(auth, request)

    @Put("/{id}")
    override fun update(auth: Authentication, @PathVariable id: Long, @Body request: SquadChangeRequest) = service.update(auth, id, request)

    @Get("/{id}")
    override fun get(auth: Authentication, @PathVariable id: Long) = service.get(auth, id)

    @Get
    override fun getAll(auth: Authentication, pageable: Pageable) = service.getAll(auth, pageable)

    @Delete("/{id}")
    override fun delete(auth: Authentication, @PathVariable id: Long) = service.delete(auth, id)

}
