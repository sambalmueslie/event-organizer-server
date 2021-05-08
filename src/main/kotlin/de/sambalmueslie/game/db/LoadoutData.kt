package de.sambalmueslie.game.db

import de.sambalmueslie.common.CrudEntity
import de.sambalmueslie.game.api.Loadout
import de.sambalmueslie.game.api.LoadoutChangeRequest
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Loadout")
@Table(name = "game_loadout")
data class LoadoutData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(unique = true)
    var text: String = "",
    @Column
    var level: Int = 0
) : CrudEntity<Loadout, LoadoutChangeRequest> {
    override fun convert() = Loadout(id, text, level)

    companion object {
        fun convert(request: LoadoutChangeRequest) = LoadoutData(0, request.text, request.level)
    }

    override fun update(request: LoadoutChangeRequest) {
        text = request.text
        level = request.level
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}


