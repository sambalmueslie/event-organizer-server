package de.sambalmueslie.clan.db

import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "Clan")
@Table(name = "clan")
data class ClanData(
    @Id
    var id: Long = 0,
    @Column(nullable = false, unique = true)
    var name: String = "",
    @Column(nullable = false)
    var tag: String = "",
    @Column(nullable = false)
    var discordId: String = ""
) {


    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}
