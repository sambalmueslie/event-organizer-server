package de.sambalmueslie.eventorg.event

import de.sambalmueslie.eventorg.event.api.Event
import de.sambalmueslie.eventorg.event.api.EventChangeRequest
import de.sambalmueslie.eventorg.game.MapService
import de.sambalmueslie.eventorg.game.api.MapChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import javax.inject.Inject

@MicronautTest
internal class EventServiceTest {
    @Inject
    lateinit var mapService: MapService

    @Inject
    lateinit var service: EventService

    private val auth: Authentication = mockk()

    @Test
    fun `crud for loadout`() {
        val title = "Light Machine Gunner"
        val description = "Light Machine Gunner"
        val timestamp = LocalDateTime.of(2021, 5, 7, 13, 47, 25)
        val rounds = 1
        val map = mapService.create(auth, MapChangeRequest("TestMap"))!!

        var event = service.create(auth, EventChangeRequest(title, description, timestamp, rounds, map.id))
        assertNotNull(event)
        val reference = Event(event!!.id, title, description, timestamp, rounds, map.id)
        assertEquals(reference, event)

        event = service.get(auth, event.id)
        assertEquals(reference, event)

        assertEquals(listOf(reference), service.getAll(auth, Pageable.from(0)).content)

        val update = EventChangeRequest("New Title", "New Description", LocalDateTime.of(2021, 5, 7, 14, 47, 25), 2, map.id)
        event = service.update(auth, event!!.id, update)

        assertEquals(Event(event!!.id, update.title, update.description, update.timestamp, update.rounds, update.mapId), event)

        service.delete(auth, event.id)

        assertEquals(emptyList<Event>(), service.getAll(auth, Pageable.from(0)).content)
    }
}
