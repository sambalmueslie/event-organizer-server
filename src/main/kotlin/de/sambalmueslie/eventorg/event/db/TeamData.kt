package de.sambalmueslie.eventorg.event.db

import de.sambalmueslie.eventorg.common.CrudEntity
import de.sambalmueslie.eventorg.event.api.Team
import de.sambalmueslie.eventorg.event.api.TeamChangeRequest
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Team")
@Table(name = "team")
data class TeamData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    var clanId: Long = -1,
    @Column
    var nationId: Long = -1,
    @Column
    var eventId: Long = -1
) : CrudEntity<Team, TeamChangeRequest> {
    override fun convert() = Team(id, clanId, nationId, eventId)

    companion object {
        fun convert(request: TeamChangeRequest) = TeamData(0, request.clanId, request.nationId, request.eventId)
    }

    override fun update(request: TeamChangeRequest) {
        clanId = request.clanId
        nationId = request.nationId
        eventId = request.eventId
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}

