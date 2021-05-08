package de.sambalmueslie.eventorg.clan

import de.sambalmueslie.eventorg.clan.api.Clan
import de.sambalmueslie.eventorg.clan.api.ClanChangeRequest
import de.sambalmueslie.eventorg.clan.ClanService
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class ClanServiceTest {

    @Inject
    lateinit var service: ClanService

    private val auth: Authentication = mockk()

    @Test
    fun `crud for clan`() {
        val name = "ClanName"
        val tag = "[CN]"

        var clan = service.create(auth, ClanChangeRequest(name, tag, ""))
        assertNotNull(clan)
        val reference = Clan(clan!!.id, name, tag, "")
        assertEquals(reference, clan)

        clan = service.get(auth, clan.id)
        assertEquals(reference, clan)

        assertEquals(listOf(reference), service.getAll(auth, Pageable.from(0)).content)

        val update = ClanChangeRequest("NewName", "[NT]", "asdf")
        clan = service.update(auth, clan!!.id, update)

        assertEquals(Clan(clan!!.id, update.name, update.tag, update.discordId), clan)

        service.delete(auth, clan.id)

        assertEquals(emptyList<Clan>(), service.getAll(auth, Pageable.from(0)).content)
    }
}
