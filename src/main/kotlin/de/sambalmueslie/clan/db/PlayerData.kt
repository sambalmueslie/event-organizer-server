package de.sambalmueslie.clan.db

import de.sambalmueslie.clan.api.Player
import de.sambalmueslie.clan.api.PlayerChangeRequest
import de.sambalmueslie.common.CrudEntity
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "Player")
@Table(name = "player")
data class PlayerData(
    @Id
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
