package de.sambalmueslie.eventorg.game

import de.sambalmueslie.eventorg.game.api.Map
import de.sambalmueslie.eventorg.game.api.MapChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class MapServiceTest {

    @Inject
    lateinit var service: MapService

    private val auth: Authentication = mockk()

    @Test
    fun `crud for map`() {
        val name = "Map Name"

        var map = service.create(auth, MapChangeRequest(name))
        assertNotNull(map)
        val reference = Map(map!!.id, name)
        assertEquals(reference, map)

        map = service.get(auth, map.id)
        assertEquals(reference, map)

        assertEquals(listOf(reference), service.getAll(auth, Pageable.from(0)).content)

        val update = MapChangeRequest("NewName")
        map = service.update(auth, map!!.id, update)

        assertEquals(Map(map!!.id, update.name), map)

        service.delete(auth, map.id)

        assertEquals(emptyList<Map>(), service.getAll(auth, Pageable.from(0)).content)
    }
}
