package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.clan.api.Clan
import de.sambalmueslie.eventorg.clan.api.Player
import de.sambalmueslie.eventorg.clan.api.PlayerChangeRequest
import de.sambalmueslie.eventorg.common.BaseIntegrationTest
import de.sambalmueslie.eventorg.game.api.*
import de.sambalmueslie.eventorg.game.api.Map
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class NationIntegrationTest : BaseIntegrationTest<Nation>() {

    override fun getDefaultType() = Nation::class.java
    private val baseUrl = "/api/game/nation"

    @Test
    fun `crud test for clan and player`() {
        val request =  NationChangeRequest("Germany")
        var loadout = callPost(baseUrl, request)
        val reference = Nation(loadout!!.id, request.name)
        assertEquals(reference, loadout)

        loadout = callGet("${baseUrl}/${loadout.id}")
        assertEquals(reference, loadout)

        assertEquals(listOf(reference), callGetPage(baseUrl).content)


        val update = NationChangeRequest("USA")
        loadout = callPut("${baseUrl}/${loadout.id}", update)

        assertEquals(Nation(loadout!!.id, update.name), loadout)

        doDelete("${baseUrl}/${loadout.id}")

        assertEquals(emptyList<Clan>(), callGetPage(baseUrl).content)
    }

}
