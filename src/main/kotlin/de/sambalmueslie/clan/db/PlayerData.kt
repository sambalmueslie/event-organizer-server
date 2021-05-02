package de.sambalmueslie.clan.db

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
) {

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}
