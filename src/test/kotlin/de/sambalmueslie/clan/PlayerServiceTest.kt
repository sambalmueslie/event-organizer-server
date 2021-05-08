package de.sambalmueslie.clan

import de.sambalmueslie.clan.api.Player
import de.sambalmueslie.clan.api.PlayerChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class PlayerServiceTest {

    @Inject
    lateinit var service: PlayerService

    private val auth: Authentication = mockk()

    @Test
    fun `crud for player`() {
        val name = "Player Name"
        val email = "player@email.com"

        var player = service.create(auth, PlayerChangeRequest(name, email, "", ""))
        assertNotNull(player)
        val reference = Player(player!!.id, name, email, "", "")
        assertEquals(reference, player)

        player = service.get(auth, player.id)
        assertEquals(reference, player)

        assertEquals(listOf(reference), service.getAll(auth, Pageable.from(0)).content)

        val update = PlayerChangeRequest("NewName", "newemail@email.com", "steamID", "discordId")
        player = service.update(auth, player!!.id, update)

        assertEquals(Player(player!!.id, update.name, update.email, update.steamId, update.discordId), player)

        service.delete(auth, player.id)

        assertEquals(emptyList<Player>(), service.getAll(auth, Pageable.from(0)).content)
    }
}
