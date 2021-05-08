package de.sambalmueslie.eventorg.clan


import de.sambalmueslie.eventorg.clan.api.Clan
import de.sambalmueslie.eventorg.clan.api.ClanChangeRequest
import de.sambalmueslie.eventorg.common.BaseIntegrationTest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class ClanIntegrationTest : BaseIntegrationTest<Clan>() {

    override fun getDefaultType() = Clan::class.java
    private val baseUrl = "/api/clan"

    @Test
    fun `crud test for clan and player`() {
        val request = ClanChangeRequest("ClanName", "[CN]", "")
        var clan = callPost(baseUrl, request)
        val reference = Clan(clan!!.id, request.name, request.tag, request.discordId)
        assertEquals(reference, clan)

        clan = callGet("${baseUrl}/${clan.id}")
        assertEquals(reference, clan)

        assertEquals(listOf(reference), callGetPage(baseUrl).content)


        val update = ClanChangeRequest("NewName", "[NT]", "asdf")
        clan = callPut("${baseUrl}/${clan.id}", update)

        assertEquals(Clan(clan!!.id, update.name, update.tag, update.discordId), clan)

        doDelete("${baseUrl}/${clan.id}")

        assertEquals(emptyList<Clan>(), callGetPage(baseUrl).content)
    }

}
