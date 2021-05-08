package de.sambalmueslie.eventorg.event.db

import de.sambalmueslie.eventorg.common.CrudEntity
import de.sambalmueslie.eventorg.event.api.Event
import de.sambalmueslie.eventorg.event.api.EventChangeRequest
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "Event")
@Table(name = "event")
data class EventData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    var title: String = "",
    @Column
    var description: String = "",
    @Column
    var timestamp: LocalDateTime = LocalDateTime.now(),
    @Column
    var rounds: Int = 1,
    @Column
    var mapId: Long = -1
) : CrudEntity<Event, EventChangeRequest> {
    override fun convert() = Event(id, title, description, timestamp, rounds, mapId)

    companion object {
        fun convert(request: EventChangeRequest) = EventData(0, request.title, request.description, request.timestamp, request.rounds, request.mapId)
    }

    override fun update(request: EventChangeRequest) {
        title = request.title
        description = request.description
        timestamp = request.timestamp
        rounds = request.rounds
        mapId = request.mapId
    }

    @Column
    val created: ZonedDateTime? = null

    @Column
    val modified: ZonedDateTime? = null
}

