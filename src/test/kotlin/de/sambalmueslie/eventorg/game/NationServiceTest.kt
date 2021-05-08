package de.sambalmueslie.eventorg.game

import de.sambalmueslie.eventorg.game.api.Nation
import de.sambalmueslie.eventorg.game.api.NationChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class NationServiceTest {

    @Inject
    lateinit var service: NationService

    private val auth: Authentication = mockk()

    @Test
    fun `crud for nation`() {
        val name = "Germany"

        var nation = service.create(auth, NationChangeRequest(name))
        assertNotNull(nation)
        val reference = Nation(nation!!.id, name)
        assertEquals(reference, nation)

        nation = service.get(auth, nation.id)
        assertEquals(reference, nation)

        assertEquals(listOf(reference), service.getAll(auth, Pageable.from(0)).content)

        val update = NationChangeRequest("New Germany")
        nation = service.update(auth, nation!!.id, update)

        assertEquals(Nation(nation!!.id, update.name), nation)

        service.delete(auth, nation.id)

        assertEquals(emptyList<Nation>(), service.getAll(auth, Pageable.from(0)).content)
    }
}
