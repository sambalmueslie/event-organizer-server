package de.sambalmueslie.game.db

import de.sambalmueslie.common.CrudEntity
import de.sambalmueslie.game.api.UnitSlot
import de.sambalmueslie.game.api.UnitSlotChangeRequest
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "UnitSlot")
@Table(name = "game_unit_slot")
data class UnitSlotData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    var text: String = "",
    @Column
    var leader: Boolean = false,
    @Column
    var unitId: Long = -1
) : CrudEntity<UnitSlot, UnitSlotChangeRequest> {
    override fun convert() = UnitSlot(id, text, leader, unitId)

    companion object {
        fun convert(request: UnitSlotChangeRequest) = UnitSlotData(0, request.text, request.leader, request.unitId)
    }

    override fun update(request: UnitSlotChangeRequest) {
        text = request.text
        leader = request.leader
        unitId = request.unitId
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}


