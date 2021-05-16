package de.sambalmueslie.eventorg.event.db

import de.sambalmueslie.eventorg.common.CrudEntity
import de.sambalmueslie.eventorg.event.api.SquadMember
import de.sambalmueslie.eventorg.event.api.SquadMemberChangeRequest
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "SquadMember")
@Table(name = "squad_member")
data class SquadMemberData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    var playerId: Long = -1,
    @Column
    var unitSlotId: Long = -1,
) : CrudEntity<SquadMember, SquadMemberChangeRequest> {
    override fun convert() = SquadMember(id, playerId, unitSlotId)

    companion object {
        fun convert(request: SquadMemberChangeRequest) = SquadMemberData(0, request.playerId, request.unitSlotId)
    }

    override fun update(request: SquadMemberChangeRequest) {
        playerId = request.playerId
        unitSlotId = request.unitSlotId
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}
