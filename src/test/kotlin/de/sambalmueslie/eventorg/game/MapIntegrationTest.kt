package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.clan.api.Clan
import de.sambalmueslie.eventorg.clan.api.Player
import de.sambalmueslie.eventorg.clan.api.PlayerChangeRequest
import de.sambalmueslie.eventorg.common.BaseIntegrationTest
import de.sambalmueslie.eventorg.game.api.Loadout
import de.sambalmueslie.eventorg.game.api.LoadoutChangeRequest
import de.sambalmueslie.eventorg.game.api.Map
import de.sambalmueslie.eventorg.game.api.MapChangeRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class MapIntegrationTest : BaseIntegrationTest<Map>() {

    override fun getDefaultType() = Map::class.java
    private val baseUrl = "/api/game/map"

    @Test
    fun `crud test for clan and player`() {
        val request =  MapChangeRequest("Map Name")
        var loadout = callPost(baseUrl, request)
        val reference = Map(loadout!!.id, request.name)
        assertEquals(reference, loadout)

        loadout = callGet("${baseUrl}/${loadout.id}")
        assertEquals(reference, loadout)

        assertEquals(listOf(reference), callGetPage(baseUrl).content)


        val update = MapChangeRequest("NewName")
        loadout = callPut("${baseUrl}/${loadout.id}", update)

        assertEquals(Map(loadout!!.id, update.name), loadout)

        doDelete("${baseUrl}/${loadout.id}")

        assertEquals(emptyList<Clan>(), callGetPage(baseUrl).content)
    }

}
