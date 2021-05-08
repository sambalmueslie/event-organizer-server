package de.sambalmueslie.game.db

import de.sambalmueslie.common.CrudEntity
import de.sambalmueslie.game.api.Unit
import de.sambalmueslie.game.api.UnitChangeRequest
import de.sambalmueslie.game.api.UnitType
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Unit")
@Table(name = "game_unit")
data class UnitData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    @Enumerated(EnumType.STRING)
    var type: UnitType = UnitType.INFANTERIE,
    @Column
    var size: Int = 0,
    @Column
    var nationId: Long = -1
) : CrudEntity<Unit, UnitChangeRequest> {
    override fun convert() = Unit(id, type, size, nationId)

    companion object {
        fun convert(request: UnitChangeRequest) = UnitData(0, request.type, request.size, request.nationId)
    }

    override fun update(request: UnitChangeRequest) {
        type = request.type
        size = request.size
        nationId = request.nationId
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}

