package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.clan.api.Clan
import de.sambalmueslie.eventorg.clan.api.Player
import de.sambalmueslie.eventorg.clan.api.PlayerChangeRequest
import de.sambalmueslie.eventorg.common.BaseIntegrationTest
import de.sambalmueslie.eventorg.game.api.Loadout
import de.sambalmueslie.eventorg.game.api.LoadoutChangeRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class LoadoutIntegrationTest : BaseIntegrationTest<Loadout>() {

    override fun getDefaultType() = Loadout::class.java
    private val baseUrl = "/api/game/loadout"

    @Test
    fun `crud test for clan and player`() {
        val request = LoadoutChangeRequest("Light Machine Gunner",1)
        var loadout = callPost(baseUrl, request)
        val reference = Loadout(loadout!!.id, request.text, request.level)
        assertEquals(reference, loadout)

        loadout = callGet("${baseUrl}/${loadout.id}")
        assertEquals(reference, loadout)

        assertEquals(listOf(reference), callGetPage(baseUrl).content)


        val update = LoadoutChangeRequest("Heavy Machine Gunner", 3)
        loadout = callPut("${baseUrl}/${loadout.id}", update)

        assertEquals(Loadout(loadout!!.id, update.text, update.level), loadout)

        doDelete("${baseUrl}/${loadout.id}")

        assertEquals(emptyList<Clan>(), callGetPage(baseUrl).content)
    }

}
