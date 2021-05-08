package de.sambalmueslie.eventorg.clan.db

import de.sambalmueslie.eventorg.clan.api.Player
import de.sambalmueslie.eventorg.clan.api.PlayerChangeRequest
import de.sambalmueslie.eventorg.common.CrudEntity
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Player")
@Table(name = "player")
data class PlayerData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false)
    var name: String = "",
    @Column(unique = true)
    var email: String = "",
    @Column(nullable = false)
    var steamId: String = "",
    @Column(nullable = false)
    var discordId: String = ""
) : CrudEntity<Player, PlayerChangeRequest> {
    override fun convert() = Player(id, name, email, steamId, discordId)

    companion object {
        fun convert(request: PlayerChangeRequest) = PlayerData(0, request.name, request.email, request.steamId, request.discordId)
    }

    override fun update(request: PlayerChangeRequest) {
        name = request.name
        email = request.email
        steamId = request.steamId
        discordId = request.discordId
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}
