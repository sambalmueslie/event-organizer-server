package de.sambalmueslie.eventorg.game.db

import de.sambalmueslie.eventorg.common.CrudEntity
import de.sambalmueslie.eventorg.game.api.Nation
import de.sambalmueslie.eventorg.game.api.NationChangeRequest
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Nation")
@Table(name = "game_nation")
data class NationData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, unique = true)
    var name: String = "",
) : CrudEntity<Nation, NationChangeRequest> {
    override fun convert() = Nation(id, name)

    companion object {
        fun convert(request: NationChangeRequest) = NationData(0, request.name)
    }

    override fun update(request: NationChangeRequest) {
        name = request.name
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}


