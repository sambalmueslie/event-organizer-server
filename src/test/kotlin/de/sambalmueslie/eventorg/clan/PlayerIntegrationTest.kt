package de.sambalmueslie.eventorg.clan


import de.sambalmueslie.eventorg.clan.api.Clan
import de.sambalmueslie.eventorg.clan.api.Player
import de.sambalmueslie.eventorg.clan.api.PlayerChangeRequest
import de.sambalmueslie.eventorg.common.BaseIntegrationTest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class PlayerIntegrationTest : BaseIntegrationTest<Player>() {

    override fun getDefaultType() = Player::class.java
    private val baseUrl = "/api/player"

    @Test
    fun `crud test for clan and player`() {
        val request = PlayerChangeRequest("Player Name", "player@email.com", "", "")
        var player = callPost(baseUrl, request)
        val reference = Player(player!!.id, request.name, request.email, request.steamId, request.discordId)
        assertEquals(reference, player)

        player = callGet("${baseUrl}/${player.id}")
        assertEquals(reference, player)

        assertEquals(listOf(reference), callGetPage(baseUrl).content)


        val update = PlayerChangeRequest("NewName", "newemail@email.com", "steamID", "discordId")
        player = callPut("${baseUrl}/${player.id}", update)

        assertEquals(Player(player!!.id, update.name, update.email, update.steamId, update.discordId), player)

        doDelete("${baseUrl}/${player.id}")

        assertEquals(emptyList<Clan>(), callGetPage(baseUrl).content)
    }

}
