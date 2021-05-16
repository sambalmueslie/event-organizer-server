package de.sambalmueslie.eventorg.event.db


import de.sambalmueslie.eventorg.common.CrudEntity
import de.sambalmueslie.eventorg.event.api.Squad
import de.sambalmueslie.eventorg.event.api.SquadChangeRequest
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Squad")
@Table(name = "squad")
data class SquadData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    var teamId: Long = -1,
    @Column
    var unitId: Long = -1,
) : CrudEntity<Squad, SquadChangeRequest> {
    override fun convert() = Squad(id, teamId, unitId)

    companion object {
        fun convert(request: SquadChangeRequest) = SquadData(0, request.teamId, request.unitId)
    }

    override fun update(request: SquadChangeRequest) {
        teamId = request.teamId
        unitId = request.unitId
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}
