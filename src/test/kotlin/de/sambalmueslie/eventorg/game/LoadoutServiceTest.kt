package de.sambalmueslie.eventorg.game

import de.sambalmueslie.eventorg.game.api.*
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class LoadoutServiceTest {

    @Inject
    lateinit var service: LoadoutService

    private val auth: Authentication = mockk()

    @Test
    fun `crud for loadout`() {
        val text = "Light Machine Gunner"
        val level = 1

        var loadout = service.create(auth, LoadoutChangeRequest(text, level))
        assertNotNull(loadout)
        val reference = Loadout(loadout!!.id, text, level)
        assertEquals(reference, loadout)

        loadout = service.get(auth, loadout.id)
        assertEquals(reference, loadout)

        assertEquals(listOf(reference), service.getAll(auth, Pageable.from(0)).content)

        val update = LoadoutChangeRequest("Heavy Machine Gunner", 3)
        loadout = service.update(auth, loadout!!.id, update)

        assertEquals(Loadout(loadout!!.id, update.text, update.level), loadout)

        service.delete(auth, loadout.id)

        assertEquals(emptyList<Loadout>(), service.getAll(auth, Pageable.from(0)).content)
    }
}
