package de.sambalmueslie.eventorg.clan


import de.sambalmueslie.eventorg.clan.api.ClanAPI
import de.sambalmueslie.eventorg.clan.api.ClanChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication

@Controller("/api/clan")
class ClanController(private val service: ClanService) : ClanAPI {
    @Post
    override fun create(auth: Authentication, @Body request: ClanChangeRequest) = service.create(auth, request)

    @Put("/{id}")
    override fun update(auth: Authentication, @PathVariable id: Long, @Body request: ClanChangeRequest) = service.update(auth, id, request)

    @Get("/{id}")
    override fun get(auth: Authentication, @PathVariable id: Long) = service.get(auth, id)

    @Get
    override fun getAll(auth: Authentication, pageable: Pageable) = service.getAll(auth, pageable)

    @Delete("/{id}")
    override fun delete(auth: Authentication, @PathVariable id: Long) = service.delete(auth, id)


}
