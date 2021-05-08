package de.sambalmueslie.clan.db

import de.sambalmueslie.clan.api.Clan
import de.sambalmueslie.clan.api.ClanChangeRequest
import de.sambalmueslie.common.CrudEntity
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Clan")
@Table(name = "clan")
data class ClanData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, unique = true)
    var name: String = "",
    @Column(nullable = false)
    var tag: String = "",
    @Column(nullable = false)
    var discordId: String = ""
) : CrudEntity<Clan, ClanChangeRequest> {
    override fun convert() = Clan(id, name, tag, discordId)

    companion object {
        fun convert(request: ClanChangeRequest) = ClanData(0, request.name, request.tag, request.discordId)
    }

    override fun update(request: ClanChangeRequest) {
        name = request.name
        tag = request.tag
        discordId = request.discordId
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}
