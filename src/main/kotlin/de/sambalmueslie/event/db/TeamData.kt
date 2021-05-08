package de.sambalmueslie.event.db

import de.sambalmueslie.common.CrudEntity
import de.sambalmueslie.event.api.Team
import de.sambalmueslie.event.api.TeamChangeRequest
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Team")
@Table(name = "team")
data class TeamData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    var clanId: Long,
    @Column
    var nationId: Long
) : CrudEntity<Team, TeamChangeRequest> {
    override fun convert() = Team(id, clanId, nationId)

    companion object {
        fun convert(request: TeamChangeRequest) = TeamData(0, request.clanId, request.nationId)
    }

    override fun update(request: TeamChangeRequest) {
        clanId = request.clanId
        nationId = request.nationId
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}

