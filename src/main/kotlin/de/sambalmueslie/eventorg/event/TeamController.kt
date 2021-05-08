package de.sambalmueslie.eventorg.event


import de.sambalmueslie.eventorg.event.api.TeamAPI
import de.sambalmueslie.eventorg.event.api.TeamChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication

@Controller("/api/team")
class TeamController(private val service: TeamService) : TeamAPI {
    @Post
    override fun create(auth: Authentication, @Body request: TeamChangeRequest) = service.create(auth, request)

    @Put("/{id}")
    override fun update(auth: Authentication, @PathVariable id: Long, @Body request: TeamChangeRequest) = service.update(auth, id, request)

    @Get("/{id}")
    override fun get(auth: Authentication, @PathVariable id: Long) = service.get(auth, id)

    @Get
    override fun getAll(auth: Authentication, pageable: Pageable) = service.getAll(auth, pageable)

    @Delete("/{id}")
    override fun delete(auth: Authentication, @PathVariable id: Long) = service.delete(auth, id)


}
